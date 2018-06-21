package com.lh.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * @author lh
 * @datetime 2018/6/21 22:59
 */

public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private UserProfileDao mUserProfileDao = null;

    private DatabaseManager(){

    }

    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    private static final class Holder{
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }

    private void initDao(Context context){
        final ReleaseOpenHelper releaseOpenHelper = new ReleaseOpenHelper(context,"fast_ec.db");
        final Database database = releaseOpenHelper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
        mUserProfileDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao(){
        return mUserProfileDao;
    }

}
