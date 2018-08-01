/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phoebe.eqx.smoi.message.parser.bos;

import ec02.utils.Log;
import phoebe.eqx.main.EquinoxMessageParser;
import phoebe.eqx.model.dcc.cca.DiameterCreditControlAnswer;

/**
 *
 * @author pavarisa
 */
public class DiameterCCA {

    public static DiameterCreditControlAnswer createDiameterCreditControlAnswer(String input) {
        EquinoxMessageParser parser = EquinoxMessageParser.newInstance();
        parser.setEquinoxMessage(input);
        DiameterCreditControlAnswer cca = null;
        try {
            cca = parser.getDiameterTranslator().getCcaTranslator().translateCCA();
        } catch (Exception e) {
            Log.e("Error Massage:" + e.getMessage(), e);
        }
        return cca;
    }
}
