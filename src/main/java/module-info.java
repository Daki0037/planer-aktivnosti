module me.danilo.planeraktivnosti {
    requires javafx.controls;
    requires javafx.fxml;
    requires httpclient;
    requires httpcore;
    requires org.json;


    opens me.danilo.planeraktivnosti to javafx.fxml;
    opens me.danilo.planeraktivnosti.controllers to javafx.fxml;

    exports me.danilo.planeraktivnosti;

}