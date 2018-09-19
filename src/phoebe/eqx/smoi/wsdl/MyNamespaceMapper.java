package phoebe.eqx.smoi.wsdl;

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

public class MyNamespaceMapper extends NamespacePrefixMapper {

//	@Override
//	public String getPreferredPrefix(String arg0, String arg1, boolean arg2) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	private static final String Info_PREFIX = "ns2"; // DEFAULT NAMESPACE
    private static final String Info_URI = "http://www.asiainfo.com/obd/InfoSyncDefines.obd";

    private static final String Common_PREFIX = "";
    private static final String Common_URI = "http://www.asiainfo.com/obd/CommonComponents.obd";
   
    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        if(Info_URI.equals(namespaceUri)) {
            return Info_PREFIX;
        } else if(Common_URI.equals(namespaceUri)) {
            return Common_PREFIX;
        } 
        return suggestion;
    }

    @Override
    public String[] getPreDeclaredNamespaceUris() {
        return new String[] { Info_URI, Common_URI };
    }

}
