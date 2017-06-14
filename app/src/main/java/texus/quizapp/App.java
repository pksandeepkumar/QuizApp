package texus.quizapp;

import android.app.Application;
import android.content.Context;

import texus.app.database.DatabaseManager;
import texus.app.database.DatabasesHelper;

/**
 * This class used to Base class for maintaining global application state
 * Created by SBL on 4/20/2016.
 */
public class App extends Application {


    private static App instance;

    public App() {
        this.instance = this;
        loadInitialTasks();
    }

    public static Context getInstance() {
        return instance;
    }

    private void loadInitialTasks() {
        DatabaseManager.initializeInstance(new DatabasesHelper(this));
    }

}
