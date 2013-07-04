package normalization;

public class BasicNormalization implements INormalizator {

    public static final String FROM = "[from]";
    public static final String TO = "[to]";
    
	@Override
	public String normalizeCategory(String subCategoryName, String fromName, String toName) {
        String normalized;
        normalized = subCategoryName.replaceAll(fromName, FROM);
        return normalized.replaceAll(toName, TO);
    }

    @Override
    public String normalizePage(String pageName, String fromName, String toName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
