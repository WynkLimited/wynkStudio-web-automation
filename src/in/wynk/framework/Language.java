package in.wynk.framework;

public enum Language {
  KANNADA("ಕನ್ನಡ"), HINDI("हिंदी"), ENGLISH("English"), MALAYALAM("മലയാളം"), TAMIL(
      "தமிழ்"), BENGALI("বাংলা"), TELUGU("తెలుగు"), MARATHI("मराठी");
  private final String value;

  private Language(final String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return getValue();
  }

  public static Language getLanguage(String text) {
    switch (text.trim().toLowerCase()) {
      case "english":
        return Language.ENGLISH;
      case "hindi":
        return Language.HINDI;
      case "kannada":
        return Language.KANNADA;
      case "malayalam":
        return Language.MALAYALAM;
      case "tamil":
        return Language.TAMIL;
      case "bengali":
        return Language.BENGALI;
      case "telugu":
        return Language.TELUGU;
      case "marathi":
        return Language.MARATHI;
      default:
        return Language.ENGLISH;
    }
  }
}