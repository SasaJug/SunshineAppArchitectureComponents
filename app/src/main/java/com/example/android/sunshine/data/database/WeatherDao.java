package com.example.android.sunshine.data.database;

/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;

/**
 * {@link Dao} which provides an api for all data operations
 */
@Dao
public interface WeatherDao {
    /**
     * Gets the weather for a single day
     *
     * @param date The date you want weather for
     * @return Weather for a single day
     */
    @Query("SELECT * FROM weather WHERE date = :date")
    WeatherEntry getWeatherByDate(Date date);

    /**
     * Inserts a list of {@link WeatherEntry} into the weather table. If there is a conflicting id
     * or date the weather entry uses the {@link OnConflictStrategy} of replacing the weather
     * forecast. The required uniqueness of these values is defined in the {@link WeatherEntry}.
     *
     * @param weather A list of weather forecasts to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(WeatherEntry... weather);

}