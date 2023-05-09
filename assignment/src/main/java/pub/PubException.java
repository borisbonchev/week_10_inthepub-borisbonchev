package pub;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
class PubException extends Exception {

    Object[] params;

    public PubException( String defaultMessage, Object... params ) {
        super( defaultMessage );
        this.params = params;
    }

    @Override
    public String getLocalizedMessage() {
        return getMessageOrDefault();
    }

    public String getMessageOrDefault() {
        //TODO do the work to compose the message with params
        ResourceBundle bundle = ResourceBundle.getBundle("pub.inthepub", Locale.getDefault());
        return LanguageUtils.getMessageOrDefault(bundle, this.getMessage(), this.getMessage());
    }
}
