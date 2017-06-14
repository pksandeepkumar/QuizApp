
package texus.app.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import texus.app.model.Question;


public class DatabasesHelper extends SQLiteOpenHelper {

	Context context;
	private static final String TAG = DatabasesHelper.class.getName();
	public static final String DATABASE_NAME = "Kilban.db";
	public static final int DATABASE_VERSION = 1;

	private static ArrayList<String> query_create_tables = null;
	private static ArrayList<String> tables = null;

	public DatabasesHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		loadQuery();
		try {
			for (String query : query_create_tables) {
//				LOG.log("Query:", "Query:" + query);
				database.execSQL(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}

	/**
	 * Create list of crate table queries. 
	 */
	private void loadQuery() {
		query_create_tables = new ArrayList<String>();
		query_create_tables.add(Question.CREATE_TABLE_QUERY);
//        query_create_tables.add(Distributor.CREATE_TABLE_QUERY);
//		query_create_tables.add(ProductCategory.CREATE_TABLE_QUERY);
//		query_create_tables.add(ProductItem.CREATE_TABLE_QUERY);
//		query_create_tables.add(Route.CREATE_TABLE_QUERY);
//		query_create_tables.add(SalesOfficer.CREATE_TABLE_QUERY);
//		query_create_tables.add(Shop.CREATE_TABLE_QUERY);
//		query_create_tables.add(OrderInfo.CREATE_TABLE_QUERY);
//		query_create_tables.add(OrderDetails.CREATE_TABLE_QUERY);
//		query_create_tables.add(TourPlan.CREATE_TABLE_QUERY);
//		query_create_tables.add(TravelExpense.CREATE_TABLE_QUERY);

	}

	private void loadTableNames() {
		tables.add(Question.TABLE_NAME);
//		tables.add(Distributor.TABLE_NAME);
//		tables.add(ProductCategory.TABLE_NAME);
//		tables.add(ProductItem.TABLE_NAME);
//		tables.add(Route.TABLE_NAME);
//		tables.add(SalesOfficer.TABLE_NAME);
//		tables.add(Shop.TABLE_NAME);
//		tables.add(OrderInfo.TABLE_NAME);
//		tables.add(OrderDetails.TABLE_NAME);
//		tables.add(TourPlan.TABLE_NAME);
//		tables.add(TravelExpense.TABLE_NAME);


	}

	public static void clearDB(Context context) {
		if( context ==  null) return;
		DatabasesHelper databasesHelper = new DatabasesHelper(context);
		clearDB(databasesHelper);
		databasesHelper.close();
	}

	public static void clearDB( DatabasesHelper databasesHelper) {
		if(databasesHelper == null) return;
//		Area.deleteTable(databasesHelper);
//		Distributor.deleteTable(databasesHelper);
	}
	



	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
						  int newVersion) {
//		LOG.log(TAG, "Upgrading database from version " + oldVersion + " to "
//				+ newVersion + ", which will destroy all old data");
		try {
			loadTableNames();
			for (String table_name : tables) {
				database.execSQL("DROP TABLE " + table_name + "; ");
			}
		} catch (SQLException e) {
//			LOG.log(TAG, "drop table error:" + e.getMessage());
		}
		onCreate(database);
	}


}
