package normalization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicNormalization implements INormalizator {

    private String from = "[from]";
    private String to = "[to]";
    
    public BasicNormalization() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("strings.cfg"));
            this.from = properties.getProperty("static.from");
            this.to = properties.getProperty("static.to");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BasicNormalization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BasicNormalization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
	@Override
	public String normalizeCategory(String subCategoryName, String fromName, String toName) {
        String normalized;
        normalized = subCategoryName.replaceAll(fromName, this.getFrom());
        return normalized.replaceAll(toName, this.getTo());
    }

    @Override
    public String normalizePage(String pageName, String fromName, String toName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }
    
}
