package controllers;

import com.example.bdmaven.HelloApplication;
import formController.formItemDetailsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import queryController.SortedCustomerController;
import queryController.SortedDiscountController;
import queryController.SortedPaymentController;
import queryController.TransactionTotalController;

import java.io.IOException;

public class MenuController {
    private Scene scene;
    private HelloApplication app;

    public void setScene(Scene scene) {
        this.scene = scene;
    }


    @FXML
    public void listitemdetails() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listItemdetails.fxml"));
            scene.setRoot((Parent) loader.load());
            ListItemDetailsController listItemDetailsController = loader.getController();
            listItemDetailsController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void customer() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelCustomer.fxml"));
            scene.setRoot((Parent) loader.load());
            CustomerController customerController = loader.getController();
            customerController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void kategori() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listKategori.fxml"));
            scene.setRoot((Parent) loader.load());

            KategoriController kategoriController = loader.getController();
            kategoriController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Discount() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listDiscount.fxml"));
            scene.setRoot((Parent) loader.load());

            DiscountController discountController = loader.getController();
            discountController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void hargaDelivery() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelHargaDelivery.fxml"));
            scene.setRoot((Parent) loader.load());
            HargaDeliveryController hargaDeliveryController = loader.getController();
            hargaDeliveryController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Delivery() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelDelivery.fxml"));
            scene.setRoot((Parent) loader.load());
            DeliveryController deliveryController = loader.getController();
            deliveryController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void jobs () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelJobs.fxml"));
            scene.setRoot((Parent) loader.load());
            JobsController jobsController = loader.getController();
            jobsController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void employee () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelEmployees.fxml"));
            scene.setRoot((Parent) loader.load());
            EmployeeController employeeController = loader.getController();
            employeeController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void Transaction () {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelTransaction.fxml"));
            scene.setRoot((Parent) loader.load());

            TransactionController transactionController = loader.getController();
            transactionController.setScene(scene);

        } catch (IOException e) {
                throw new RuntimeException(e);
        }
    }
    public void Payment () {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelPayment.fxml"));
            scene.setRoot((Parent) loader.load());

            PaymentController paymentController = loader.getController();
            paymentController.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        public void Items () {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/listItems.fxml"));
                scene.setRoot((Parent) loader.load());

                ItemController itemController = loader.getController();
                itemController.setScene(scene);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void DiscountDetail(){
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelDiscountDetails.fxml"));
                scene.setRoot((Parent) loader.load());
                DiscountDetailController discountDetailController = loader.getController();
                discountDetailController.setScene(scene);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void totalPenjualan(){
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/showTotalTransaction.fxml"));
                scene.setRoot((Parent) loader.load());

                TransactionTotalController controller = loader.getController();
                controller.setScene(scene);
                controller.setApp(app);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void sortCustomer(){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelSortedCustomer.fxml"));
                scene.setRoot((Parent) loader.load());
                SortedCustomerController controller = loader.getController();
                controller.setScene(scene);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void sortPayment(){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelSortedPayment.fxml"));
                scene.setRoot((Parent) loader.load());
                SortedPaymentController controller = loader.getController();
                controller.setScene(scene);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void sortDiscount(){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdmaven/tabelSortedDiscount.fxml"));
                scene.setRoot((Parent) loader.load());
                SortedDiscountController controller = loader.getController();
                controller.setScene(scene);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public Scene getScene () {
            return scene;
        }


}

