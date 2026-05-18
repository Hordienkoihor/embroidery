module org.kma.summerpractice.embroidery {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens org.kma.summerpractice.embroidery to javafx.fxml;
    exports org.kma.summerpractice.embroidery;
}