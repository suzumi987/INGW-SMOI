package phoebe.eqx.smoi.state;

import phoebe.eqx.smoi.control.AFState;
import ec02.af.abstracts.AbstractAFStateManager;

public class AFStateManager extends AbstractAFStateManager {

    public AFStateManager(String state) {

        this.afState = null;
        if (state.equals(AFState.IDLE)) {
            this.afState = new StateIdle();
        } else if (state.equals(AFState.W_DS2A)) {
            this.afState = new StateWaitDS2A();
        } else if (state.equals(AFState.W_BOS_DCC)) {
            this.afState = new StateWaitBOS();
        } else if (state.equals(AFState.W_AVATAR)) {
            this.afState = new StateWaitAVATAR();
        } else if (state.equals(AFState.W_BSSbroker)) {
            this.afState = new StateWaitBSSbroker();
        } else if (state.equals(AFState.W_PPGW)) {
            this.afState = new StateWaitPPGW();
        } else if (state.equals(AFState.W_SMOI)) {
            this.afState = new StateWaitSMOI();
        } else if (state.equals(AFState.W_INS)) {
            this.afState = new StateWaitINS();
        } else if (state.equals(AFState.W_E01_DESTINATION)) {
            this.afState = new StateWaitE01Destination();
        } else if (state.equals(AFState.W_EQL)) {
            this.afState = new StateWaitEQL();
        }  else if (state.equals(AFState.W_MD)) {
            this.afState = new StateWaitMD();
        } else {
            this.afState = new StateIdle();
        }
    }
}
