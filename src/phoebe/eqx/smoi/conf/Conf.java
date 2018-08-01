/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phoebe.eqx.smoi.conf;

/**
 *
 * @author pavarisa
 */
public class Conf {
    //Log Name
    public static final String logNameSummary = "LogName-Summary";
    public static final String logNameDetail = "LogName-Detail";
    
    //Log Enable
    public static final String logEnableSummary = "Write-LogSummary";
    public static final String logEnableDetail = "Write-LogDetail";
    //Resource Name Active
    public static final String resourceNameDs2a_Active = "Resource-Name-Active-DS2A";
    public static final String resourceNameBos_Active = "Resource-Name-Active-BOS";
    public static final String resourceNameAVATAR_Active = "Resource-Name-Active-AVATAR";
    public static final String resourceNameEQL_Active = "Resource-Name-Active-EQL";
    public static final String resourceNameBssbroker_Active = "Resource-Name-Active-BSSBroker";
    public static final String resourceNamePpgw_Active = "Resource-Name-Active-PPGW_FET";
    public static final String resourceNameSmoi_Active = "Resource-Name-Active-SMOI_FET";
    public static final String resource_Name_Active_MD = "Resource-Name-Active-MD";
    
    //Resource Name Standby
    public static final String resourceNameDs2a_Standby = "Resource-Name-Standby-DS2A";
    public static final String resourceNameBos_Standby = "Resource-Name-Standby-BOS";
    public static final String resourceNameAVATAR_Standby = "Resource-Name-Standby-AVATAR";
    public static final String resourceNameEQL_Standby = "Resource-Name-Standby-EQL";
    public static final String resourceNameBssbroker_Standby = "Resource-Name-Standby-BSSBroker";
    public static final String resourceNamePpgw_Standby = "Resource-Name-Standby-PPGW_FET";
    public static final String resourceNameSmoi_Standby = "Resource-Name-Standby-SMOI_FET";
    public static final String resource_Name_Standby_MD = "Resource-Name-Standby-MD";
    
    //Resource Name Standby Max Retry
    public static final String resourceNameDs2a_Standby_MaxRetry = "Resource-Name-Standby-DS2A-MaxRetry";
    public static final String resourceNameBos_Standby_MaxRetry = "Resource-Name-Standby-BOS-MaxRetry"; 
    public static final String resourceNameAVATAR_Standby_MaxRetry = "Resource-Name-Standby-AVATAR-MaxRetry";
    public static final String resourceNameEQL_Standby_MaxRetry = "Resource-Name-Standby-EQL-MaxRetry";
    public static final String resourceNameBssbroker_Standby_MaxRetry = "Resource-Name-Standby-BSSBroker-MaxRetry";
    public static final String resourceNamePpgw_Standby_MaxRetry = "Resource-Name-Standby-PPGW_FET-MaxRetry";
    public static final String resourceNameSmoi_Standby_MaxRetry = "Resource-Name-Standby-SMOI_FET-MaxRetry";
    public static final String resourceNameE01_Standby_MaxRetry = "Resource-Name-Standby-E01-MaxRetry";
    public static final String resource_Name_Standby_MD_MaxRetry  = "Resource-Name-Standby-MD-MaxRetry";
    //Timeout
    public static final String ds2aTimeout="Tm-DS2A";
    public static final String bosTimeout="Tm-BOS";
    public static final String AVATARTimeout="Tm-AVATAR";
    public static final String EQLTimeout="Tm-EQL";
    public static final String bssTimeout="Tm-BSSBroker";
    public static final String ppgwTimeout="Tm-PPGW_FET";
    public static final String smoiTimeout="Tm-SMOI_FET";
    public static final String tm_MD = "Tm-MD";
    //SMOI
    public static final String ingwSmoi_OriginHost = "INGW_SMOI-Origin-Host";
    public static final String ingwSmoi_OriginRealm = "INGW_SMOI-Origin-Realm";
    //BOS
    public static final String bosLocation = "BOS-Location";

    //AVATAR
    public static final String avatarLocation = "AVATAR-Location";
    //EQL
    public static final String balanceType = "AVATAR-Free-Unit-ID-PRMMONEY";
    public static final String freeUnitId_PRMMINUTE = "AVATAR-Free-Unit-ID-PRMMINUTE";
    public static final String freeUnitId_PRMSM = "AVATAR-Free-Unit-ID-PRMSM";
    public static final String freeUnitId_FREERBTSONG = "AVATAR-Free-Unit-ID-FREERBTSONG";
    public static final String BALANCETYPE_PRMMONEY = "AVATAR-BalanceType-Reward-Pocket";
    public static final String mainBalanceType = "AVATAR-BalanceType-MainBalance";
    public static final String avatar_channel = "AVATAR-Channel";
    public static final String avatar_ajd_channel_id = "AVATAR-Adjust-Channel-ID";
    
    //public static final String bos_DestinationHost = "BOS-Destination-Host";
    //public static final String bos_DestinationRealm = "BOS-Destination-Realm";
    public static final String bos_CCR_Access_Method = "CCR-Access-Method";
    public static final String setPPSReward_BOS_DCC = "setPPSReward-BOS-DCC";
     
    public static final String bos_CCR_Resource_Id_PRMMONEY = "Resource-Id-PRMMONEY";
    public static final String bos_CCR_Resource_Id_PRMSM = "Resource-Id-PRMSM";
    public static final String bos_CCR_Resource_Id_PRMMINUTE = "Resource-Id-PRMMINUTE";
    public static final String bos_CCR_Resource_Id_FREECALLTIMES = "Resource-Id-FREECALLTIMES";
    public static final String bos_CCR_Resource_Id_FREERBTSONG = "Resource-Id-FREERBTSONG";
    
    //Support INS
    public static final String resourceNameIns_Active = "Resource-Name-Active-INS";
    public static final String resourceNameIns_Standby = "Resource-Name-Standby-INS";
    public static final String insLocation = "INS-Location";
    public static final String insTimeout="Tm-INS";
    public static final String resourceNameIns_Standby_MaxRetry = "Resource-Name-Standby-INS-MaxRetry";

    //AVATAR
    public static final String avatar_balance_SMS_Domestic = "AVATAR-BalanceType-SMS-Domestic-Prefix";
    public static final String avatar_balance_Voice_Domestic = "AVATAR-BalanceType-Voice-Domestic-Prefix";
    public static final String avatar_balance_Money ="AVATAR-BalanceType-Money-Prefix";
    public static final String avatar_balance_RTBSong = "AVATAR-BalanceType-RBTSong-Prefix";
    public static final String ModifyWLAlternateSolutionFlag = "Modify-WL-Alternate-Solution-Flag";
    
    // URL
    public static final String url_eql = "URL-EQL";
    public static final String url_MD = "URL-MD";
    
    //MD
    public static final String md_Login_User = "MD-Login-User";
    public static final String md_Login_Password = "MD-Login-Password";
}
