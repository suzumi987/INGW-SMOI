package phoebe.eqx.smoi.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ec02.utils.Log;

public class JAXBUtils {
	
    public static JAXBContext jaxbContext;

    private static  JAXBContext getJaxbContext() {
        if(jaxbContext == null) {
            try {
            	ClassLoader classLoader = phoebe.eqx.smoi.wsdl.InfoSyncDefines.ObjectFactory.class.getClassLoader();
                jaxbContext = JAXBContext.newInstance("phoebe.eqx.smoi.wsdl.InfoSyncDefines", classLoader);
            } catch (JAXBException ex) {
            	Log.e("Error Massage:" + ex.getMessage(), ex);
            }
        }
        return jaxbContext;
    }
    public static Unmarshaller getUnmarshaller() {
        Unmarshaller unmarshaller = null;
        try {
            JAXBContext jaxbContext = getJaxbContext();
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException ex){
        	Log.e("Error Massage:" + ex.getMessage(), ex);
        }
        return unmarshaller;
    }

    public static Marshaller getMarshaller() {
        Marshaller marshaller = null;
        try {
            JAXBContext jaxbContext = getJaxbContext();
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException ex) {
        	Log.e("Error Massage:" + ex.getMessage(), ex);
        }
        return marshaller;
    }
}
