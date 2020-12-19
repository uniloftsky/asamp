package uniloftsky.springframework.asamp.services;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uniloftsky.springframework.asamp.model.ItemRemove;

import java.io.IOException;
import java.util.Set;

public interface ItemRemoveService {

    Set<ItemRemove> findAll();
    ItemRemove findById(Long id);
    void delete(ItemRemove itemRemove);
    ItemRemove save(ItemRemove itemRemove, Long itemTypeId);
    ItemRemove save(ItemRemove itemRemove);
    void write(String fileName) throws IOException;
    Sheet createSheet(XSSFWorkbook workbook);
    void createHeader(XSSFWorkbook workbook, Sheet sheet);
    void createCells(XSSFWorkbook workbook, Sheet sheet);

}
