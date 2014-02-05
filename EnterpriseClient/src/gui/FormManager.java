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
    
    private FormManager(){
        frmLogin=new FrmLogin();
        frmMain=new FrmMain();
    }
    //-----------------END-SINGLETON-------------------

    
    
    FrmLogin frmLogin;
    FrmMain frmMain;

    public FrmLogin getFrmLogin() {
        return frmLogin;
    }

    public void setFrmLogin(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }

    public void setFrmMain(FrmMain frmMain) {
        this.frmMain = frmMain;
    }
    
    
    
}
