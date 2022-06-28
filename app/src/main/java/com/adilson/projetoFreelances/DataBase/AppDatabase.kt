package com.adilson.projetoFreelances.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adilson.projetoFreelances.DataBase.dao.freelaDAO
import com.adilson.projetoFreelances.model.Freelas


@Database(entities = [Freelas::class], version = 1)

abstract class AppDatabase : RoomDatabase() {

    abstract fun freelasDao() : freelaDAO

    companion object {

        @Volatile
        private lateinit var db: AppDatabase

        fun getInstance(context: Context): AppDatabase{
            if (::db.isInitialized) return db
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "freeladb.db"
            ).allowMainThreadQueries().build().also { db = it }
        }
    }

}


/*

    Volatile -> Para nao ficar recriando banco de dados em toda a instancia, pois isso consume muita memoria.

* entities:  O entites é justamente para determinarmos quais foram as entidades que configuramos dentro do nosso projeto,
*  que o Room vai conseguir configurar e se comunicar com SQLite,
*  seja gerando as tabelas, fazendo os comportamentos a partir dos DAOs e assim por diante.
*
*
* version:  Basicamente, quando falamos de versão, é justamente a versão que o nosso banco de dados está.
*  E o que isso implica? Isso implica em uma técnica que chamamos de migração ou migration.
*  Nessa técnica temos a possibilidade de ajustar o nosso banco de dados conforme mudanças.
*  O que acontece? Nesse nosso momento, que estamos fazendo a nossa configuração, temos a nossa classe produto.
*  Ela é a primeira versão do nosso banco de dados, com nome, data, local, noivos e assim por diante.
*
*
*   Se tiver um tipo que nao pertence ao BD, se usa pacotes de converts.
*
* */
