package com.prueba.mantenedor.dao;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import com.prueba.mantenedor.model.PautaPoc;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PautaPocRepository {
    private static final String FILE_PATH = "/static/pautas_poc.xlsx";

    public List<PautaPoc> findAll() {

        List<PautaPoc> list = new ArrayList<>();

        try (InputStream is = getClass().getResourceAsStream(FILE_PATH)) {

            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            boolean skipHeader = true;

            for (Row row : sheet) {

                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                Integer numPoc = (int) row.getCell(0).getNumericCellValue();
                String codTratamiento = row.getCell(1).getStringCellValue();
                String nombrePauta = row.getCell(2).getStringCellValue();
                String areaSolicitante = row.getCell(3).getStringCellValue();
                String actCarga = row.getCell(4).getStringCellValue();
                String infoSpsReg = row.getCell(5).getStringCellValue();
                String observaciones = row.getCell(6).getStringCellValue();

                list.add(new PautaPoc(
                        numPoc,
                        codTratamiento,
                        nombrePauta,
                        areaSolicitante,
                        actCarga,
                        infoSpsReg,
                        observaciones));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
