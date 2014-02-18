/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author jaspertomas
 */
public class FormManager {
    
    //------------------SINGLETON-------------------
    private static FormManager instance=null;

    public static FormManager getInstance() {
        if(instance==null)
        {
            instance=new FormManager();
        }
        return instance;
    }
    //-----------------END-SINGLETON-------------------
    
    FrmLogin frmLogin;
    FrmMain frmMain;
    FrmInvoice frmInvoice;
    FrmPurchaseOrder frmPurchaseOrder;
    FrmDSR frmDSR;
    FrmAccountsReceivable frmAccountsReceivable;
    FrmAccountsPayable frmAccountsPayable;
    
    private FormManager(){
        frmLogin=new FrmLogin();
        frmMain=new FrmMain();
        frmInvoice=new FrmInvoice();
        frmPurchaseOrder=new FrmPurchaseOrder();
        frmDSR=new FrmDSR();
        frmAccountsReceivable=new FrmAccountsReceivable();
        frmAccountsPayable=new FrmAccountsPayable();
    }
    //-----------------END-SINGLETON-------------------

    

    public FrmLogin getFrmLogin() {
        return frmLogin;
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }

    public FrmInvoice getFrmInvoice() {
        return frmInvoice;
    }

    public FrmPurchaseOrder getFrmPurchaseOrder() {
        return frmPurchaseOrder;
    }

    public FrmDSR getFrmDSR() {
        return frmDSR;
    }

    public FrmAccountsReceivable getFrmAccountsReceivable() {
        return frmAccountsReceivable;
    }

    public FrmAccountsPayable getFrmAccountsPayable() {
        return frmAccountsPayable;
    }
    
    
    
}
