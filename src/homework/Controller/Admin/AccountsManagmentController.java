/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import static Controller.Admin.UsersManagmentController.selectedUserToUpdate;
import static Controller.Admin.UsersManagmentController.updateStage;
import Model.Account;
import Model.User;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class AccountsManagmentController implements Initializable {
    
    public static Account selectedAccountToUpdate;
    public static Stage updateStage;

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button showAllAccountsBtn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private Button searchAccountBtn;
    @FXML
    private TextField accontSearchTF;
    @FXML
    private TableView<Account> accountableView;
    @FXML
    private TableColumn<Account, Integer> idcol;
    @FXML
    private TableColumn<Account, Integer> accountNumberCol;
    @FXML
    private TableColumn<Account, String> usernameCol;
    @FXML
    private TableColumn<Account, String> currencyCol;
    @FXML
    private TableColumn<Account, Double> balanceCol;
    @FXML
    private TableColumn<Account, String> creationDateCol;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idcol.setCellValueFactory(new PropertyValueFactory("id"));
        accountNumberCol.setCellValueFactory(new PropertyValueFactory("accountNumber"));
        usernameCol.setCellValueFactory(new PropertyValueFactory("userName"));
        currencyCol.setCellValueFactory(new PropertyValueFactory("carrency"));
        balanceCol.setCellValueFactory(new PropertyValueFactory("balance"));
        creationDateCol.setCellValueFactory(new PropertyValueFactory("creationDate"));
    }    

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
         ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
                 ViewManager.adminPage.changeSceneToAccountsManagment();
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void showAccountCreationPage(ActionEvent event) {
                ViewManager.adminPage.changeSceneToCreateAccount();

    }

    @FXML
    private void showAllAccounts(ActionEvent event)throws SQLException, ClassNotFoundException {
           ObservableList<Account> AccountList =
           FXCollections.observableArrayList(Account.getAllUsers());
      
      accountableView.setItems(AccountList);
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) throws IOException{
        //check if there is an user selected from the TableView
        if(accountableView.getSelectionModel().getSelectedItem() != null){
        //store the selected user from the TableView in our global var user selectedUserToUpdate   
        selectedAccountToUpdate = accountableView.getSelectionModel().getSelectedItem();
        //load update page fxml
        FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/UpdateAccount.fxml"));
        Parent rootUpdate = loaderUpdate.load();     
        Scene updateAccountScene = new Scene(rootUpdate); 
        //store loaded fxml in our global stage updateStage and show it
        updateStage = new Stage();
        updateStage.setScene(updateAccountScene);
        updateStage.setTitle("Update Account " + selectedAccountToUpdate.getAccountNumber());
        updateStage.show();
        }
    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) throws SQLException {
        //check if there is an user selected from the TableView
        if(accountableView.getSelectionModel().getSelectedItem() != null){
            //store the selected user from the TableView in new user object
            Account selectedaccount = accountableView.getSelectionModel().getSelectedItem();
            
            //show an confirmation alert and make the deletion on confirm event
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Account delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this account ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    //delete the selected user from database table using delete method in our User model
                    selectedaccount.delete();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
            deletedSuccessAlert.setTitle("account deleted");
            deletedSuccessAlert.setContentText("account deleted");
            deletedSuccessAlert.show();  
            }
            });
        
        }else{
        Alert warnAlert = new Alert(Alert.AlertType.WARNING);
        warnAlert.setTitle("Select an account");
        warnAlert.setContentText("Please select an account from the table view");
        warnAlert.show();  
        }
    }
    

    @FXML
    private void searchForAnAccount(ActionEvent event) {
    }
    
}
