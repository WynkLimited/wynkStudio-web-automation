package in.wynk.framework;

public enum LanguageMapping {
  Hindi("hi"),English("en"),Tamil("ta"),Telugu("te"),
  Kannada("kn"),Malyalam("ml"),Gujrati("gu"),Punjabi("pa"),
  Bengali("bn"),Marathi("mr"),Oriya("or"),Rajasthani("ra"),
  Bhojpuri("bh"),Pahari("ph"),Assamese("as"),Urdu("ur"),
  French("fr");

  private String languageCode;

  LanguageMapping(String languageCode) {
    this.languageCode = languageCode;
  }

  public String getLanguageCode() {
    return languageCode;
  }
}
