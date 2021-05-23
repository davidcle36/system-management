/** Enum Constant for FXML files */
package utils;

/**
 * Enum of Form Type {@link #MAIN_FORM}
 */
public enum FxmlPath {
    /**
     * Path to MainForm.fxml
     */
    MAIN_FORM {
        @Override
        public String toString() {
            return "/view/MainView.fxml";
        }
    },
    CUSTOMER_FORM {
        @Override
        public String toString() {
            return "/view/CustomerView.fxml";
        }
    },
    APPOINTMENT_FORM{
        @Override
        public String toString() {
            return "/view/AppointmentView.fxml";
        }
    },
    RECORD_FORM{
        @Override
        public String toString() {
            return "/view/Record.fxml";
        }
    },
}
