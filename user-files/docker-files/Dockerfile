FROM openjdk:8-jdk

ARG MAVEN_VERSION=3.5.4
ARG USER_HOME_DIR="/root"
ARG SHA=ce50b1c91364cb77efe3776f756a6d92b76d9038b0a0782f7d53acf1e997a14d
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha256sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

COPY mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
COPY settings-docker.xml /usr/share/maven/ref/

#Google Chrome
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
&& echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
&& apt-get update -qqy \
&& apt-get -qqy install google-chrome-stable \
&& rm /etc/apt/sources.list.d/google-chrome.list \
&& rm -rf /var/lib/apt/lists/ /var/cache/apt/ \
&& sed -i 's/"$HERE\/chrome"/"$HERE\/chrome" --no-sandbox/g' /opt/google/chrome/google-chrome

ARG CHROME_DRIVER_VERSION="latest"
RUN CD_VERSION=$(if [ ${CHROME_DRIVER_VERSION:-latest} = "latest" ]; then echo $(wget -qO- https://chromedriver.storage.googleapis.com/LATEST_RELEASE); else echo $CHROME_DRIVER_VERSION; fi) \
&& echo "Using chromedriver version: "$CD_VERSION \
&& wget --no-verbose -O /tmp/chromedriver_linux64.zip https://chromedriver.storage.googleapis.com/$CD_VERSION/chromedriver_linux64.zip \
&& rm -rf /opt/selenium/chromedriver \
&& unzip /tmp/chromedriver_linux64.zip -d /opt/selenium \
&& rm /tmp/chromedriver_linux64.zip \
&& mv /opt/selenium/chromedriver /opt/selenium/chromedriver-$CD_VERSION \
&& chmod 755 /opt/selenium/chromedriver-$CD_VERSION \
&& ln -fs /opt/selenium/chromedriver-$CD_VERSION /usr/bin/chromedriver

COPY pom.xml /tmp/pom.xml
COPY settings.xml /usr/share/maven/ref/
RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
RUN mvn org.apache.maven.plugins:maven-dependency-plugin:2.8:get -Dartifact=com.trivago.rta:cluecumber-report-plugin:2.0.1 -s /usr/share/maven/ref/settings-docker.xml

RUN mkdir ${USER_HOME_DIR}/src
WORKDIR ${USER_HOME_DIR}/src
RUN chmod +x /usr/local/bin/mvn-entrypoint.sh
ENTRYPOINT ["/usr/local/bin/mvn-entrypoint.sh"]
CMD ["mvn"]