package com.manager.barcode.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.manager.barcode.models.Barcode;
import com.manager.barcode.repository.BarcodeRowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultBarcodeRepository implements BarcodeRepository{
    
    public static final String FIND_BY_ID_SQL = "select * from Barcode where id=?";
    public static final String FIND_BY_BARCODE_SQL = "select * from Barcode where barcode=?";

    private final JdbcTemplate jdbcTemplate;

    public DefaultBarcodeRepository(final JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional < Barcode > findById(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID_SQL, new Object[] {
                id
            },
            new BeanPropertyRowMapper < Barcode > (Barcode.class)));
    }

    public int insertBarcode(Barcode barcode) {
        try{
        //return jdbcTemplate.update("insert into Barcode (barcode) " + "values(?)",
         //   new Object[] {
           //     barcode.getBarcode()
          //  });

          SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
          simpleJdbcInsert.withTableName("barcode").usingGeneratedKeyColumns("id");
          Map<String, Object> parameters = new HashMap<String, Object>(1);
          parameters.put("barcode", barcode.getBarcode());
          Number insertedId = simpleJdbcInsert.executeAndReturnKey(parameters);
          return insertedId.intValue();
        }catch(org.springframework.dao.DataIntegrityViolationException ex)
        {
            return -1;
        }
        }
        
        public Optional < Barcode > findByBarcode(String barcode) {
            Barcode barcodeEnt = null;
            try
           {
            barcodeEnt =  jdbcTemplate.queryForObject(FIND_BY_BARCODE_SQL, 
           new Object[] { barcode },
           new BarcodeRowMapper());
        } catch (org.springframework.dao.EmptyResultDataAccessException e) 
        {barcodeEnt = null;
        }
            return Optional.ofNullable(barcodeEnt);
        }
    }