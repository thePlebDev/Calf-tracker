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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Calf calf);
    //WE CAN ADD THE UPDATE/DELETE LATE
    @Delete
    void delete(Calf calf);

    @Update
    public void updateCalf(Calf calf);


    @Query("DELETE FROM calf_table")
    void deleteAll();

    @Query("SELECT * FROM calf_table ORDER BY id ASC")
    LiveData<List<Calf>> getAllCalves();

    //CALL A QUERY TO GET ONLY A SPECIFIC CALF
    //WHERE FILTERS OUT UNWANTED ROWS
    @Query("SELECT * FROM calf_table WHERE id = :calfId")
    public Calf getCalf(int calfId);

    @Query("SELECT * FROM calf_table WHERE tagNumber = :tagNumber")
    LiveData<List<Calf>> searchCalfTagNumber(String tagNumber);
}
