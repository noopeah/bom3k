package domain.implementations;

import com.google.inject.Guice;
import com.google.inject.Injector;
import data.database.CredentialsManager;
import data.database.Database;
import data.exceptions.UserIsNotLoggedInException;
import data.models.UserAccountDataModel;
import di.modules.DataModule;
import domain.interfaces.ChangeUserPreferencesUseCase;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeUserPreferencesUseCaseImpl implements ChangeUserPreferencesUseCase {

    CredentialsManager credentialsManager;
    Database database;

    public ChangeUserPreferencesUseCaseImpl() {
        Injector injector = Guice.createInjector(new DataModule());
        credentialsManager = injector.getInstance(CredentialsManager.class);
        database = injector.getInstance(Database.class);
    }

    @Override
    public void setUserCountry(String country) {
        try {
            UserAccountDataModel user = credentialsManager.getLastLoggedInUser();
            user.setCountry(country);
            database.alterUser(user);
        } catch (UserIsNotLoggedInException ex) {
            Logger lgr = Logger.getLogger(ChangeUserPreferencesUseCase.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void setUserCity(String city) {
        try {
            UserAccountDataModel user = credentialsManager.getLastLoggedInUser();
            user.setCity(city);
            database.alterUser(user);
        } catch (UserIsNotLoggedInException ex) {
            Logger lgr = Logger.getLogger(ChangeUserPreferencesUseCase.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void setUserCurrency(String currency) {
        try {
            UserAccountDataModel user = credentialsManager.getLastLoggedInUser();
            user.setCurrency(currency);
            database.alterUser(user);
        } catch (UserIsNotLoggedInException ex) {
            Logger lgr = Logger.getLogger(ChangeUserPreferencesUseCase.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
