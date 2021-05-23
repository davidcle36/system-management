/**
 * Translation Class
 * */
package utils;

/**
 * Translation Class
 * */
public class Translation {
    private static String fr = "fr";

    public enum LOGIN{
        LOGIN{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "S''identifier";
                }
                else{
                    return "Login";
                }
            }
        },
        USERNAME{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Nom d''utilisateur";
                }
                else{
                    return "Username";
                }
            }
        },
        USERNAME_PROMPT{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Entrez votre nom d'utilisateur ici.";
                }
                else{
                    return "Enter username here";
                }
            }
        },
        PASSWORD_PROMPT{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Entrez le mot de passe ici.";
                }
                else{
                    return "Enter password here";
                }
            }
        },
        PASSWORD{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Mot de passe";
                }
                else{
                    return "Password";
                }
            }
        },
        CANCEL{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Annuler";
                }
                else{
                    return "Cancel";
                }
            }
        },
        UNAUTHORIZED{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Impossible de trouver l''utilisateur. Veuillez réessayer.";
                }
                else{
                    return "Unable to find user. Please to try again.";
                }
            }
        }
    }

    public enum MAIN{
        ADD_CUSTOMER{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Ajouter un client";
                }
                else{
                    return "Add Customer";
                }
            }
        },
        UPDATE_CUSTOMER{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Mettre à jour le client";
                }
                else{
                    return "Update Customer";
                }
            }
        },
        DELETE_CUSTOMER{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Supprimer le client";
                }
                else{
                    return "Delete Customer";
                }
            }
        },
        ADD_APPOINTMENT{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Ajouter un rendez-vous";
                }
                else{
                    return "Add Appointment";
                }
            }
        },
        UPDATE_APPOINTMENT{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Mettre à jour le rendez-vous";
                }
                else{
                    return "Update Appointment";
                }
            }
        },
        DELETE_APPOINTMENT{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Supprimer le rendez-vous";
                }
                else{
                    return "Delete Appointment";
                }
            }
        },
        CUSTOMERS{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Les clients";
                }
                else{
                    return "Customers";
                }
            }
        },
        APPOINTMENTS{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Rendez-vous";
                }
                else{
                    return "Appointments";
                }
            }
        },
    }

    public enum CUSTOMER{
        NAME{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Nom";
                }
                else{
                    return "Name";
                }
            }
        },
        ADDRESS{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Adresse";
                }
                else{
                    return "Address";
                }
            }
        },
        POSTAL_CODE{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Code Postal";
                }
                else{
                    return "Postal Code";
                }
            }
        },
        PHONE{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Téléphone";
                }
                else{
                    return "Phone";
                }
            }
        },
        CREATE_DATE{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "créer un rendez-vous";
                }
                else{
                    return "Create Date";
                }
            }
        },
        CREATE_BY{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Crée par";
                }
                else{
                    return "Create By";
                }
            }
        },
        LAST_UPDATE{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Dernière mise à jour";
                }
                else{
                    return "Last Update";
                }
            }
        },
        LAST_UPDATE_BY{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Dernière mise à jour par";
                }
                else{
                    return "Last Update By";
                }
            }
        },
        DIVISION_ID{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "ID de Division";
                }
                else{
                    return "Division ID";
                }
            }
        },
        COUNTRY{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Pays";
                }
                else{
                    return "Please Select a Country.";
                }
            }
        },
        COUNTRY_SELECT{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Veuillez sélectionner un pays.";
                }
                else{
                    return "Please Select a Country.";
                }
            }
        },
        DIVISION_SELECT{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Veuillez sélectionner une division.";
                }
                else{
                    return "Please Select a Division.";
                }
            }
        }
    }

    public enum APPOINTMENT{
        APPOINTMENT_ID{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "ID de Rendez-vous";
                }
                else{
                    return "Appointment ID";
                }
            }
        },
        SELECT_CONTACT{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Sélectionnez un contact.";
                }
                else{
                    return "Select a contact.";
                }
            }
        },
        TITLE{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Titre";
                }
                else{
                    return "Title";
                }
            }
        },
        DESCRIPTION{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "La description";
                }
                else{
                    return "Description";
                }
            }
        },
        LOCATION{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Emplacement";
                }
                else{
                    return "Location";
                }
            }
        },
        CONTACT_ID{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "ID de contact";
                }
                else{
                    return "Contact ID";
                }
            }
        },
        CONTACT_NAME{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Contact Nom";
                }
                else{
                    return "Contact Name";
                }
            }
        },
        TYPE{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Catégorie";
                }
                else{
                    return "Type";
                }
            }
        },
        START{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Début";
                }
                else{
                    return "Start";
                }
            }
        },
        END{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Fin";
                }
                else{
                    return "End";
                }
            }
        },
        CUSTOMER_ID{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "ID de cliente";
                }
                else{
                    return "Customer ID";
                }
            }
        },
        NO_APPOINTMENT{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Vous n'avez pas de rendez-vous aujourd'hui.";
                }
                else{
                    return "You have no appointment today.";
                }
            }
        },
        UPCOMING_APPOINTMENT{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Prochain rendez-vous avec pièce d'identité ";
                }
                else{
                    return "Upcoming appointment with ID ";
                }
            }
        }
    }

    public enum ERROR{
        EMPTY_SELECT{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Please select an item on the list.";
                }
                else{
                    return "Please select an item on the list.";
                }
            }
        },
        MISSING_FIELD{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Veuillez remplir tout les champs.";
                }
                else{
                    return "Please fill in all the fields.";
                }
            }
        },
        APPOINTMENT_CONSTRAINT{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Impossible de supprimer en raison de l'ouverture du rendez-vous.";
                }
                else{
                    return "Unable to delete due to appointment being open.";
                }
            }
        },
        END_DATE{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Vous atteignez la fin des dates possibles.";
                }
                else{
                    return "You reach the end of possible dates.";
                }
            }
        },
        WITHIN_RANGE_TIME{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "L'heure doit être comprise entre 8:00 a.m et 10:00 p.m EST";
                }
                else{
                    return "The time must be within 8:00 a.m to 10:00 p.m EST";
                }
            }
        },
        TIME_OVERLAY{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "La date et l'heure de début doivent précéder la date et l'heure de fin.";
                }
                else{
                    return "The starting date and time must come before the ending date and time.";
                }
            }
        },
        OVERLAPPED{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Rendez-vous chevauché avec un client.";
                }
                else{
                    return "Appointment Overlapped with a customer.";
                }
            }
        }
    }

    public enum COMMON{
        AUTO_GENERATED{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Genere automatiquement";
                }
                else{
                    return "Auto Generated";
                }
            }
        },
        SAVE{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Sauver";
                }
                else{
                    return "Save";
                }
            }
        },
        CANCEL{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Annuler";
                }
                else{
                    return "Cancel";
                }
            }
        }
    }

    public enum CONFIRMATION {
        DELETE_C{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Voulez-vous supprimer le client?";
                }
                else{
                    return "Do you want to delete the customer?";
                }
            }
        },
        DELETE_A{
            @Override
            public String toString(){
                if(SystemInfo.getLanguage().contains(fr)){
                    return "Voulez-vous supprimer le rendez-vous?";
                }
                else{
                    return "Do you want to delete the appointment?";
                }
            }
        }
    }
}
