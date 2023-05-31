/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Account;
import Model.User;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class UpdateAccountsController implements Initializable {
    
    private Account oldAccount;

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Button updateUserBtn;
    @FXML
    private Button cancelBtn;
    private DatePicker DateTF;
    @FXML
    private TextField BalanceTF;
    @FXML
    private TextField UserNameTF;
    @FXML
    private TextField AccountsNumTF;
    @FXML
    private TextField CarrencyTF;
    @FXML
    private TextField CreationDateTF;
    @FXML
    private TextField useridTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //get selected user data from UsersManagmentController updatedUser var
        this.oldAccount = Controller.Admin.AccountsManagmentController.selectedAccountToUpdate;
        
        //set text field's data the same as updatedUser data
        useridTF.setText(Integer.toString(oldAccount.getUserid()));
        AccountsNumTF.setText(Integer.toString(oldAccount.getAccountNumber()));
        UserNameTF.setText(oldAccount.getUserName());
        CarrencyTF.setText(oldAccount.getCarrency());
        BalanceTF.setText(Double.toString(oldAccount.getBalance()));
        CreationDateTF.setText(oldAccount.getCreationDate());
    }    

    @FXML
    private void updateUser(ActionEvent event) throws SQLException, ClassNotFoundException {
         //get the new data from text field's and store it in new user object
        String userid=useridTF.getText();
        String AccountsNum = AccountsNumTF.getText();
        String UserName = UserNameTF.getText();
        String Carrency = CarrencyTF.getText();
        String Balance = BalanceTF.getText();
        String CreationDate = CreationDateTF.getText();
        
        
        //make an new user object having this data
        Account newaccount = new Account(Integer.parseInt(userid),Integer.parseInt(AccountsNum), UserName, Carrency, Double.parseDouble(Balance), CreationDate);
        
        //set the new user id the same as the old user
        newaccount.setId(oldAccount.getId());
        
        // update the user in database by update method
        newaccount.update();
        
        //close the update stage using our global stage var in UsersManagmentController and show an alert
        Controller.Admin.UsersManagmentController.updateStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User updated");
        alert.setContentText("User updated");
        alert.showAndWait();
    }

    @FXML
    private void cancelCreation(ActionEvent event) {
      Controller.Admin.AccountsManagmentController.updateStage.close(); 

    }

    @FXML
    private void CreationDateTF(ActionEvent event) {
    }
    
}
