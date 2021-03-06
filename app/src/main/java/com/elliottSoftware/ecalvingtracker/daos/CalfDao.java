package com.elliottSoftware.ecalvingtracker.daos;

import com.elliottSoftware.ecalvingtracker.models.Calf;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CalfDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) //REP TESTED
    long insert(Calf calf) throws Exception;

    @Delete
    int delete(Calf calf); //REP TESTED

    @Update
    int updateCalf(Calf calf) throws Exception; //REP TESTED


    @Query("DELETE FROM calf_table") //REP TESTED
    int deleteAll() throws Exception;

    @Query("SELECT * FROM calf_table ORDER BY id ASC")
    LiveData<List<Calf>> getAllCalves();

    //CALL A QUERY TO GET ONLY A SPECIFIC CALF
    //WHERE FILTERS OUT UNWANTED ROWS
    @Query("SELECT * FROM calf_table WHERE id = :calfId")
     Calf getCalf(int calfId);

    @Query("SELECT * FROM calf_table WHERE tagNumber = :tagNumber")
    LiveData<List<Calf>> searchCalfTagNumber(String tagNumber);

    @Insert
    Long properInsert(Calf calf) throws Exception;

}
