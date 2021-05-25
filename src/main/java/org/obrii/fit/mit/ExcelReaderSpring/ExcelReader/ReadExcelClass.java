
package org.obrii.fit.mit.ExcelReaderSpring.ExcelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.obrii.fit.mit.ExcelReaderSpring.FileReader.FileName;
import org.obrii.fit.mit.ExcelReaderSpring.Table.CellT;
import org.obrii.fit.mit.ExcelReaderSpring.Table.RowT;
import org.obrii.fit.mit.ExcelReaderSpring.Table.SheetT;
import org.obrii.fit.mit.ExcelReaderSpring.Table.Table;
import org.obrii.fit.mit.ExcelReaderSpring.Table.TableCloud;
//@author NEVM PC
 
public class ReadExcelClass {
   
static String URL = "";
static Table tableMain = new Table(); 
static ArrayList<String> VAL = new ArrayList<>();
static ArrayList<String> POS = new ArrayList<>();
static ArrayList<String> HIE = new ArrayList<>();
//static ArrayList<String> TableList = new ArrayList<>();
static ArrayList<String> HeightList = new ArrayList<>();


//private static final String  FILES_NAME = "C:\\Users\\NEVM PC\\Documents\\NetBeansProjects\\ReadExcel\\Test2Excel.xlsx"; 
   //  ReadExcel()     main(String [] args)                       
public static void ReadExcel(){
    

    //1x1 sell value
    ArrayList<String> stringRow = new ArrayList<String>();
    //1x1 sell id_row
    ArrayList<String> intRow = new ArrayList<String>();
    //nxn sell value
    ArrayList<String> stringMarg = new ArrayList<String>();
    //nxn sell id_row_start
    ArrayList<String> intMarg = new ArrayList<String>();  
    //nxn sell id_row_end_width
    ArrayList<String> rowMargEnd = new ArrayList<String>();
    //nxn sell id_sell_end_hieght
    ArrayList<String> colMargEnd = new ArrayList<String>();
    //null value
    ArrayList<String> rowNull = new ArrayList<String>();
    //null value_id
    ArrayList<String> rowNullNum = new ArrayList<String>();
    FileInputStream file = null;
    Boolean flag = true;
    ArrayList<String> rows = new ArrayList<String>();  
    //max sell in a sheets
    int max = 0;      
    //find max
    try {
        //C:\\Users\\NEVM PC\\Documents\\NetBeansProjects\\
        file = new FileInputStream( new File("file"));
        //file = new FileInputStream(new File("C:\\Users\\NEVM PC\\Documents\\NetBeansProjects\\ReadMerged\\Excel.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Sheet> sheets = workbook.sheetIterator();
        
        while(sheets.hasNext()){
        Sheet sh = sheets.next();
                //tableString.add("<h1>Sheet name is "+ sh.getSheetName()+"</h1>");
            
            Iterator<Row> rowIterator = sh.iterator();
        int rowN = 0;
        while (rowIterator.hasNext()) {                                                            
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();
            String tuple = "";
            int cellN = 0;                         
            outer:
            while (cellIterator.hasNext()) { 
                Cell cell = cellIterator.next();                 
                cellN ++;
            }
            if(cellN > max){
                max = cellN;
            }           
            flag = true;  
        }     
        }
        
    }
    catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (file != null) {
            try {
                file.close();
                file = null;
            } catch (Exception e) {
                System.out.println("File closing operation failed");
                e.printStackTrace();
            }
        }                                 
    }
    //marged biuldHelper list
     for(int i=0;i<=max;i++){
        
        HeightList.add("1");
    }
    //mainProgram
    Table table = new Table(0,FileName.getFileName());
    table.setMax(max);
    try {                                
        //C:\\Users\\NEVM PC\\Documents\\NetBeansProjects\\
        file = new FileInputStream( new File("file"));
        System.out.println(new File("file").getName()); 
        //file = new FileInputStream(new File("C:\\Users\\NEVM PC\\Documents\\NetBeansProjects\\ReadMerged\\Excel.xlsx"));
        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);        
        //Get first/desired sheet from the workbook
        Iterator<Sheet> sheets = workbook.sheetIterator();
        //sheet                        
        int shN = 0;
        while(sheets.hasNext()){        
        Sheet sh = sheets.next();
        SheetT sheet = new SheetT(shN,sh.getSheetName());
        Iterator<Row> rowIterator = sh.iterator();
        int rowN = 0;
        //TableList.add("<td rowspan='1' colspan='"+ String.valueOf(max)+ "' class=\"\">"+ sh.getSheetName()+"</td>");
        //rows
        
            while (rowIterator.hasNext()) {
                RowT rowt = new RowT(rowN);
                
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                String tuple = "";
                int cellN = 0; 
                stringRow = new ArrayList<String>();
                intRow = new ArrayList<String>();
                stringMarg = new ArrayList<String>();
                intMarg = new ArrayList<String>();
                rowMargEnd = new ArrayList<String>();
                colMargEnd = new ArrayList<String>();
                rowNull = new ArrayList<String>();
                rowNullNum = new ArrayList<String>();
                //row`s values
                outer:
                while (cellIterator.hasNext()) {                                
                    Cell cell = cellIterator.next();  
                    //FIND MARGED//                                                           
                    //will iterate over the Merged cells
                    for (int i = 0; i < sh.getNumMergedRegions(); i++) {
                        CellRangeAddress region = sh.getMergedRegion(i); //Region of merged cells
                        int colIndex = region.getFirstColumn(); //number of columns merged
                        int rowNum = region.getFirstRow();  
                        int colEnd = region.getLastColumn(); //number of columns merged
                        int rowEnd = region.getLastRow(); 
                    //number of rows merged
                    //check first cell of the region                    
                    if (rowNum == cell.getRowIndex() && colIndex == cell.getColumnIndex() && cell.getCellType() == CellType.FORMULA){                    
                    rowMargEnd.add(String.valueOf(colEnd));
                        colMargEnd.add(String.valueOf(rowEnd));
                        //stringMarg.add(String.valueOf(sh.getRow(rowNum).getCell(colIndex).getStringCellValue()));
                        stringMarg.add(String.valueOf(sh.getRow(rowNum).getCell(colIndex).getCellFormula()));
                        intMarg.add(String.valueOf(colIndex));
                        //cellt.setValue(String.valueOf(sh.getRow(rowNum).getCell(colIndex).getStringCellValue()));                        
                        //rowt.setCell(cellt);
                        continue outer;
                    }
                    else 
                        if (rowNum == cell.getRowIndex() && colIndex == cell.getColumnIndex()) {
                        rowMargEnd.add(String.valueOf(colEnd));
                        colMargEnd.add(String.valueOf(rowEnd));                        
                        stringMarg.add(String.valueOf(sh.getRow(rowNum).getCell(colIndex).getStringCellValue()));
                        //stringMarg.add(String.valueOf(sh.getRow(rowNum).getCell(colIndex).getCellFormula()));
                        intMarg.add(String.valueOf(colIndex));
                        //cellt.setValue(String.valueOf(sh.getRow(rowNum).getCell(colIndex).getStringCellValue()));
                        //rowt.setCell(cellt);
                        continue outer;
                    }
                    }   
                    //the data in merge cells is always present on the first cell. All other cells(in merged region) are considered blank
                    //FIND 1X1//|| cell.getCellType() == CellType._NONE
                    if (cell.getCellType() == CellType.FORMULA) {
                        //cell.getRowIndex();
                        DataFormatter objDefaultFormatF = new DataFormatter();    
                        String strF = objDefaultFormatF.formatCellValue(cell);                    
                        //if(str.equals("")){}else{
                        stringRow.add(strF);
                        //cellt.setValue(strF);
                        intRow.add(String.valueOf(cell.getColumnIndex()));
                        //}
                    }                 
                    if (cell.getCellType() == CellType.BLANK || cell.getCellType() == CellType._NONE) {
                        //cellt.setValue(cell.getStringCellValue());
                        rowNull.add("null");
                        rowNullNum.add(String.valueOf(cellN));                
                        cellN ++;                    
                        //System.out.print("scip"+cellN);
                        continue;
                    }           
                    //Check the cell type and format accordingly
                    if(cell.getCellType() == CellType.NUMERIC){
                        //cell.getRowIndex();
                        DataFormatter objDefaultFormat = new DataFormatter();    
                        String str = objDefaultFormat.formatCellValue(cell);                    
                        //if(str.equals("")){}else{
                        //cellt.setValue(str);
                        stringRow.add(str);
                        intRow.add(String.valueOf(cell.getColumnIndex()));
                        //}                    
                        //tuple = tuple +"row:"+String.valueOf(rowN)+"cell:"+String.valueOf(cellN)+" __  " + str +" __ "+ " + ";
                    }
                    else if (cell.getCellType() == CellType.STRING) {                                                            
                        //if(cell.getStringCellValue().equals("")){}
                        // else{
                        //cellt.setValue(cell.getStringCellValue());
                        stringRow.add(cell.getStringCellValue());
                        intRow.add(String.valueOf(cell.getColumnIndex())); 
                        //}                                       
                    }
                    //System.out.print("it"+cellN);
                    //rowt.setCell(cellt);
                    cellN ++;
                }        
                //ROW_FORMATER//
                VAL.clear();
                POS.clear();
                HIE.clear();
                //TableList.add(
                        rowFormater(rowN,intRow ,stringRow , intMarg ,stringMarg , max,colMargEnd,rowMargEnd ,rowNull, rowNullNum);
                //);                
                for(int i=0; i<POS.size();i++ ){
                    CellT cellt = new CellT(i);  
                    cellt.setValue(VAL.get(i));
                    cellt.setPossion(POS.get(i));
                    cellt.setHieght(HIE.get(i));
                    rowt.setCell(cellt);   
                }                   
                rows.add(tuple);
                flag = true;  
                sheet.setRow(rowt);                
                rowN ++;
            }  
            table.setSheet(sheet);
            shN++;
        }               
        workbook.close();        
        TableCloud cloud = new TableCloud();
        cloud.setTable(table);
        setTable(table);
    }
    catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (file != null) {
            try {
                file.close();
                file = null;
            } catch (Exception e) {
                System.out.println("File closing operation failed");
                e.printStackTrace();
            }
        }                                 
    }                
}
//ROW FORMATER METHOD//
public static void  rowFormater(int rowN,ArrayList<String> cellNum ,ArrayList<String> cellValue , ArrayList<String> margNum ,ArrayList<String> margValue, int rowSize,ArrayList<String> sellMargeEnd ,ArrayList<String> rowMargeEnd,ArrayList<String> rowNull,ArrayList<String> rowNullNum ){
    //ROW`S Value//
    ArrayList<String> mainValue = new ArrayList<String>();
    //ROW`S Value ID//
    ArrayList<String> mainPossion = new ArrayList<String>();
    //USED ID`S
    ArrayList<String> usedPossion = new ArrayList<String>();
    //BIULD HELPER
    ArrayList<String> heightPossion = new ArrayList<String>();
    //RETURN`S STRING//
    String tableRow = "";
    //SIZE OF SELL
    int sizeSell=0;                    
    //FORMATE DEFAULT ROW//
    for(int i=0;i<=rowSize;i++){
        mainPossion.add("none");
        mainValue.add("none");
        heightPossion.add("1");
    }
    //WRITE MARGED`S SELLS ID
    for(int i=0;i<margNum.size();i++){
        for(int h=0;h<=rowSize;h++){
        if(h>=Integer.valueOf(margNum.get(i))&&h<=Integer.valueOf(rowMargeEnd.get(i)))
           mainPossion.set(h, margNum.get(i));    
        }        
    }
    //WRITE MARGED`S SELLS VALUE
    for(int i=0;i<mainPossion.size();i++){
        try{
            margNum.indexOf(mainPossion.get(i));
            margValue.get(margNum.indexOf(mainPossion.get(i)));
            mainValue.set(i,margValue.get(margNum.indexOf(mainPossion.get(i))));
        }
        catch(Exception e){
        }
    }    
    //WRITE 1X1 SELLS
    for(int i=0;i<cellNum.size();i++){
        for(int h=0;h<=rowSize;h++){
            cellNum.get(i);
            cellValue.get(i);
            if(cellValue.get(i).equals(mainValue.get(h))){
            }
            else{
                if(cellNum.get(i).equals(String.valueOf(h))){ 
                    if(cellValue.get(i).equals("")){
                        if(mainValue.get(h).equals("none")){
                        mainPossion.set(h, cellNum.get(i));
                    mainValue.set(h, cellValue.get(i));
                        
                        }
                        
                    
                    }else{
                        
                        
                        mainPossion.set(h, cellNum.get(i));
                    mainValue.set(h, cellValue.get(i));
                    }                                                                                
                }               
            }                                                                                   
        }       
    }        
    //WTITE NULL SELLS//
//    for(int i=0;i<rowNullNum.size();i++){
//        for(int h=0;h<=rowSize;h++){
//            rowNullNum.get(i);
//            rowNull.get(i);
//            if(rowNull.get(i).equals(mainValue.get(h))){
//            }
//            else{
//                if(rowNullNum.get(i).equals(String.valueOf(h))&& (mainValue.get(h).equals("none") ||mainValue.get(h).equals("")   ) ){ 
//                    mainPossion.set(h, rowNullNum.get(i));
//                    mainValue.set(h,  rowNull.get(i));
//                }               
//            }                                                                                   
//        }       
//    }                 
    //ROW`S HIEGHT BILDER//    
    for(int i=0;i<margValue.size();i++){    
        for(int h=0;h<mainValue.size();h++){
            if(margValue.get(i).equals(mainValue.get(h))){     
                int height;
                height =  Integer.parseInt(sellMargeEnd.get(i)) - rowN +1;               
                heightPossion.set(h, String.valueOf(height));
            }            
        }                                              
    }
    //SET HEIGHT TO GLOBAL//
    setHeight(heightPossion);
    //usedPossion FORMATER//
    for(int i=0;i<mainPossion.size();i++){
         if(i>0 ){
            if(!mainPossion.get(i).equals(mainPossion.get(i-1))){
                usedPossion.add(mainPossion.get(i));
            }
        }else{
            usedPossion.add(mainPossion.get(i));
        }
    }  
    //ADD MAX VALUE OF SELLS IN ROW TO usedPossion//
    usedPossion.add(String.valueOf(rowSize));
    //GET HIEGHT HELPER//
     ArrayList<String> listH = HeightList;
    //ALGORITHM HIEGHT - 1
    for(int i=0;i<HeightList.size();i++){
        if(Integer.parseInt(HeightList.get(i))>1){
             HeightList.set(i,String.valueOf(Integer.parseInt(HeightList.get(i))-1));
        }
    }   
    //DELETE 1XN MARGER ERRORS//
    for(int i=0;i<margValue.size();i++){
        for(int f=0;f<mainValue.size();f++){
            if(margValue.get(i).equals(mainValue.get(f))){
                mainPossion.set(f,margNum.get(i));            
            }        
        }        
    }
    //SIZE///  
    ArrayList<String> Pos = new ArrayList<String>();         
    for(int i=0;i<mainPossion.size();i++){ 
        Pos.add( mainPossion.get(i));
    }    
    ArrayList<String> sizePos = new ArrayList<String>();
    int size = 0;       
    for(int i=0;i<usedPossion.size();i++){ 
        for(int h=0;h<Pos.size();h++){
            if(Pos.get(h).equals(usedPossion.get(i))){
                Pos.set(h,"skipp");
                size++; 
            } 
            else if(Pos.get(h).equals("skipp")){
            }        
            else{
                break;                        
            }                                 
        }  
        if(size == 0){
            sizePos.add(String.valueOf(1));
        }
        else{
            sizePos.add(String.valueOf(size));
        }    
        size = 0;    
    }    
    //0`S ROW KILLER
    boolean canWrite = false;
    for(int i=0;i<usedPossion.size();i++){
        if(usedPossion.get(i).equals("none")){
        }
        else{
            canWrite = true;
        }
    }
    ///// arr formater
    for(int i=0;i<mainValue.size();i++){
        VAL.add(mainValue.get(i));
    } 
    for(int i=0;i<mainPossion.size();i++){
        POS.add(mainPossion.get(i));
    }
    for(int i=0;i<heightPossion.size();i++){
        HIE.add(heightPossion.get(i));
    }      
    //STRING FORMATER//
//    for(int i=0;i<usedPossion.size();i++){
//        for(int h=0;h<mainPossion.size();h++){          
//            usedPossion.get(i);        
//            }        
//            try{                        
//                if(mainValue.get(mainPossion.indexOf(usedPossion.get(i))).equals("none")){
//                if(mainValue.get(0).equals("null")||mainValue.get(0).equals("")||mainValue.get(0).equals("none")){                
//                    } else{
//                    if(    mainValue.get(mainPossion.indexOf(usedPossion.get(usedPossion.size()-1))).equals("none")         ){}else{
//                    tableRow = tableRow + "<td rowspan='"+heightPossion.get(mainPossion.indexOf(usedPossion.get(i))) +"' colspan='"+sizePos.get(i)+"' style=\" width:"+String.valueOf(50*sizeSell)+"px;\"class=\""+sizeSell+"__"+usedPossion.get(i)+"\">"+ ""  +"</td>";                
//                }
//                    }
//                    
//                  //tableRow = tableRow + "<td rowspan='"+heightPossion.get(mainPossion.indexOf(usedPossion.get(i))) +"' colspan='"+sizeSell+"' style=\" width:"+String.valueOf(50*sizeSell)+"px;\"class=\""+sizeSell+"__"+usedPossion.get(i)+"\">"+  mainValue.get(mainPossion.indexOf(usedPossion.get(i)))  +"</td>";                
//                     
//                }
//                else if(mainValue.get(mainPossion.indexOf(usedPossion.get(i))).equals("null")||mainValue.get(mainPossion.indexOf(usedPossion.get(i))).equals("")||mainValue.get(mainPossion.indexOf(usedPossion.get(i))).equals("none")){
//                    if(mainValue.get(0).equals("null")||mainValue.get(0).equals("")||mainValue.get(0).equals("none")){                
//                    }                
//                    else 
//                        if(Integer.parseInt(listH.get(mainPossion.indexOf(usedPossion.get(i))))== 1){
//                        tableRow = tableRow + "<td rowspan='"+heightPossion.get(mainPossion.indexOf(usedPossion.get(i))) +"' colspan='"+sizePos.get(i)+"' style=\" width:"+String.valueOf(50*sizeSell)+"px;\"class=\""+sizeSell+"__"+usedPossion.get(i)+"\">"+  mainValue.get(mainPossion.indexOf(usedPossion.get(i)))  +"</td>";                
//                    }
//                    else{
//                    }
//            
//                    }            
//                else{
//                    tableRow = tableRow + "<td rowspan='"+heightPossion.get(mainPossion.indexOf(usedPossion.get(i))) +"' colspan='"+sizePos.get(i)+"' style=\" width:"+String.valueOf(50*sizeSell)+"px;\"class=\""+sizeSell+"__"+usedPossion.get(i)+"\">"+  mainValue.get(mainPossion.indexOf(usedPossion.get(i)))  +"</td>";
//                }
//            }
//            catch(Exception e){
//            }
//            sizeSell=0;        
//    }     
    //DIAGNOSTIC//
    //    System.out.println("used");
//for(int i=0;i<usedPossion.size();i++){
//   //heigt
//    System.out.println("cell: "+usedPossion.get(i));
//    
//   }
    //System.out.println("Size");
    //for(int i=0;i<usedPossion.size();i++){
           
           //System.out.println("pos - "+ usedPossion.get(i) +" s ="+sizePos.get(i));
           //} 
//   System.out.println("nullPos");
//for(int i=0;i<rowNullNum.size();i++){
//    //widd
//    System.out.println(""+rowNullNum.get(i));
//    
//    }  
//    
//     System.out.println("nullvals");
//for(int i=0;i<rowNull.size();i++){
//    //widd
//    System.out.println(""+rowNull.get(i));
//    
//    }            
//System.out.println("row");
//for(int i=0;i<sellMargeEnd.size();i++){
//    //heigt
//    System.out.println("cell: "+sellMargeEnd.get(i));
//    
//   }
//for(int i=0;i<rowMargeEnd .size();i++){
//    //widd
//    System.out.println("row: "+rowMargeEnd .get(i));
//    
//    }
// System.out.println("hieghtPos");
//for(int i=0;i<heightPossion.size();i++){
//    //widd
//    System.out.println(""+heightPossion.get(i));
//    
//    }
//    System.out.println("rowSize");
//    System.out.println(rowSize);
   
//System.out.println("margValue");for(int i=0;i<margValue.size();i++){System.out.println(margValue.get(i));}
  
// System.out.println("margNum");for(int i=0;i<margNum.size();i++){System.out.println(margNum.get(i));}
//
    //System.out.println("cellNum");for(int i=0;i<cellNum.size();i++){System.out.println(cellNum.get(i));}
   // System.out.println("cellValue");for(int i=0;i<cellValue.size();i++){
        
      //  if(cellValue.get(i).equals("")){
      //      }
      //  else{
     //   System.out.println(cellValue.get(i));
     //   }
     // } 
 
//        
//     System.out.println("23423423");
//    for(int i=0;i<listH.size();i++){
//        
//    System.out.println(listH.get(i));
//    
//    }
    
   // System.out.println("mainPossion");for(int i=0;i<mainPossion.size();i++){System.out.println(mainPossion.get(i));}
    
  //  System.out.println("mainValue");for(int i=0;i<mainValue.size();i++){System.out.println(mainValue.get(i));}    
    //return tableRow; 
}

//RETURN TO SERVLET METHOD//
public void getRead(){
    ReadExcel();    
}

public ArrayList<String> getList(){
    ArrayList<String>  w = new ArrayList<String>();
    return w;
}
//GET HIEGHT HELPER LIST
public static ArrayList<String> getHeight(){    
    ArrayList<String> list = HeightList;    
    for(int i=0;i<HeightList.size();i++){
        if(Integer.parseInt(HeightList.get(i))>1){
             HeightList.set(i,String.valueOf(Integer.parseInt(HeightList.get(i))-1));
        }
    }    
    return list;
}
//SET HIEGHT HELPER LIST
public static void  setHeight(ArrayList<String> list){   
    for(int i=0;i<list.size();i++){
        if(Integer.parseInt(list.get(i))>1){                    
            HeightList.set(i,String.valueOf(Integer.parseInt(list.get(i))+1));
        }                   
    }    
}

public void resetList(){
//  TableList.clear();
}

public Table getTable() {
    return tableMain;
}

public static void setTable(Table table) {
    ReadExcelClass.tableMain = table;
}

public static String getUrl() {
    return URL;
}

public static void setUrl(String string) {
    ReadExcelClass.URL = string;
}

public static String  tableFormater(Table table, boolean canWrite){
    ArrayList<String> mainPossion = new ArrayList<>();
    ArrayList<String> mainValue = new ArrayList<>();
    ArrayList<String> heightPossion = new ArrayList<>();
    ArrayList<String> HeightList = new ArrayList<>();
    int RowCounter = 0;
    for(int i=0;i<=table.getMax();i++){      
        HeightList.add("1");
    }    
    String TableBody = "<table>";
    for(int i =0 ; i< table.getSheetList().size();i++){
        TableBody = TableBody +"<td rowspan='1' colspan='"+ String.valueOf(table.getMax())+ "' >"+ table.getSheetList().get(i).getName()+"</td>";              
        for(int h =0 ; h< table.getSheetList().get(i).getRowList().size();h++){
            TableBody = TableBody + "<tr>";
                for(int j =0 ; j< table.getSheetList().get(i).getRowList().get(h).getCellList().size();j++){
                    System.out.println();
                    mainPossion.add(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getPossion());
                    mainValue.add(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue());
                    heightPossion.add(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getHieght());                 
                }                                                                             
                




                //SET HEIGHT TO GLOBAL//
                for(int v=0;v<heightPossion.size();v++){
                    if(Integer.parseInt(heightPossion.get(v))>1){                    
                    HeightList.set(v,String.valueOf(Integer.parseInt(heightPossion.get(v))+1));
                    }                   
                }   
                //GET HIEGHT HELPER//       
                ArrayList<String> listH = new ArrayList<>();    
                for(int v=0;v<HeightList.size();v++){
                    listH.add(HeightList.get(v));    
                }        
                //ALGORITHM HIEGHT - 1
                for(int v=0;v<HeightList.size();v++){
                    if(Integer.parseInt(HeightList.get(v))>1){
                        HeightList.set(v,String.valueOf(Integer.parseInt(HeightList.get(v))-1));
                    }
                }                                                                                 
                ArrayList<String> usedPossion = new ArrayList<String>();              
                //usedPossion FORMATER//
                for(int v=0;v<mainPossion.size();v++){
                    if(v>0 ){
                        if(!mainPossion.get(v).equals(mainPossion.get(v-1))){
                            usedPossion.add(mainPossion.get(v));
                        }
                    }else{
                        usedPossion.add(mainPossion.get(v));
                    }
                }  
                //ADD MAX VALUE OF SELLS IN ROW TO usedPossion//
                usedPossion.add(String.valueOf(table.getMax()));                                                                                                                        
                //SIZE///  
                ArrayList<String> Pos = new ArrayList<String>();               
                for(int v=0;v<mainPossion.size();v++){ 
                    Pos.add( mainPossion.get(v));
                }                    
                ArrayList<String> sizePos = new ArrayList<String>();
                int size = 0;          
                for(int v=0;v<usedPossion.size();v++){ 
                    for(int q=0;q<Pos.size();q++){
                        if(Pos.get(q).equals(usedPossion.get(v))){
                            Pos.set(q,"skipp");
                            size++; 
                        } 
                        else if(Pos.get(q).equals("skipp")){}
                        else{
                            break;                                                           
                        }                                  
                    }  
                    if(size == 0){sizePos.add(String.valueOf(1));}else{sizePos.add(String.valueOf(size));}                    
                    size = 0;    
                }
                // <form action="<%=request.getContextPath()%>/new" methd="get"><input type="text" name="value" value="<%=data.getName()%>"><input type="hidden" name="pos" value="<%=data.getName()%>"><input type="hidden" name="row" value="<%=data.getName()%>"><input type="submit" value="update data" ></form>                                                                                                                                
                //STRING FORMATER//    
                for(int k=0;k<usedPossion.size();k++){                  
                    try{                        
                        if(mainValue.get(mainPossion.indexOf(usedPossion.get(k))).equals("none")){
                            if(mainValue.get(0).equals("null")||mainValue.get(0).equals("")||mainValue.get(0).equals("none")){                
                            } else{
                                if(    mainValue.get(mainPossion.indexOf(usedPossion.get(usedPossion.size()-1))).equals("none")         ){}else{
                                TableBody = TableBody + "<td rowspan='"+heightPossion.get(mainPossion.indexOf(usedPossion.get(k))) +"' colspan='"+sizePos.get(k)+"' class=\""+sizePos.get(k)+"__"+usedPossion.get(k)+"\">"+ ""  +"</td>";                
                                table.getSheetList().get(i).getRowList().get(h).setWritten();
                                }
                            }                            
                            //tableRow = tableRow + "<td rowspan='"+heightPossion.get(mainPossion.indexOf(usedPossion.get(i))) +"' colspan='"+sizeSell+"' style=\" width:"+String.valueOf(50*sizeSell)+"px;\"class=\""+sizeSell+"__"+usedPossion.get(i)+"\">"+  mainValue.get(mainPossion.indexOf(usedPossion.get(i)))  +"</td>";                                            
                        }
                        else if(mainValue.get(mainPossion.indexOf(usedPossion.get(k))).equals("null")||mainValue.get(mainPossion.indexOf(usedPossion.get(k))).equals("")||mainValue.get(mainPossion.indexOf(usedPossion.get(k))).equals("none")){
                            if(mainValue.get(0).equals("null")||mainValue.get(0).equals("")||mainValue.get(0).equals("none")){                
                            }                
                            else if(Integer.parseInt(listH.get(mainPossion.indexOf(usedPossion.get(k))))== 1){
                                TableBody = TableBody + "<td rowspan='"+heightPossion.get(mainPossion.indexOf(usedPossion.get(k))) +"' colspan='"+sizePos.get(k)+"' class=\""+sizePos.get(k)+"__"+usedPossion.get(k)+"\">         ";
                                table.getSheetList().get(i).getRowList().get(h).setWritten();
                                if(canWrite){
                                    //TableBody = TableBody + "<form action=\""+ getUrl()+"/new\" methd=\"get\"><input type=\"text\" name=\"value\" value=\""+ mainValue.get(mainPossion.indexOf(usedPossion.get(k))) +"\"><input type=\"hidden\" name=\"pos\" value=\""+usedPossion.get(k)+"\"><input type=\"hidden\" name=\"row\" value=\""+String.valueOf(RowCounter) +"\"><input type=\"submit\" value=\"update data\" ></form>";                              
                                    TableBody = TableBody +  "<form class=\"form\" action=\""+ getUrl()+"/new\" methd=\"get\"><div class=\"form__input-container\"><input type=\"text\" name=\"value\" value=\""             +  mainValue.get(mainPossion.indexOf(usedPossion.get(k)))  +"\" class=\"form__input\"><input type=\"hidden\" name=\"pos\" value=\""+usedPossion.get(k)+"\"><input type=\"hidden\" name=\"row\" value=\""+String.valueOf(RowCounter) +"\"><div class=\"form__btn-container\"><button type=\"button\" class=\"form__btn\"><img class=\"form__btn-icon\" src=\""+ getUrl()+"/PagesRes/TableDisplay/cancel.svg\" alt=\"\"></button><button type=\"submit\" class=\"form__btn\"><img class=\"form__btn-icon\" src=\""+ getUrl()+"/PagesRes/TableDisplay/checked.svg\" alt=\"\"></button></div></div></form>";
                                    table.getSheetList().get(i).getRowList().get(h).setWritten();
                                }
                                else{
                                    TableBody = TableBody + mainValue.get(mainPossion.indexOf(usedPossion.get(k)));
                                }                                                                                                                
                                table.getSheetList().get(i).getRowList().get(h).setWritten();
                                TableBody = TableBody +  "</td>";                
                            }
                            else{
                            }            
                        }            
                        else{                            
                            TableBody = TableBody + "<td rowspan='"+heightPossion.get(mainPossion.indexOf(usedPossion.get(k))) +"' colspan='"+sizePos.get(k)+"' class=\""+sizePos.get(k)+"__"+usedPossion.get(k)+"\">";
                            table.getSheetList().get(i).getRowList().get(h).setWritten();
                            if(canWrite){
                                //TableBody = TableBody + " <form action=\""+ getUrl()+"/new\" methd=\"get\"><input type=\"text\" name=\"value\" value=\""             +  mainValue.get(mainPossion.indexOf(usedPossion.get(k)))  +"\"><input type=\"hidden\" name=\"pos\" value=\""+usedPossion.get(k)+"\"><input type=\"hidden\" name=\"row\" value=\""+String.valueOf(RowCounter) +"\"><input type=\"submit\" value=\"update data\" ></form>";
                            TableBody = TableBody +  "<form class=\"form\" action=\""+ getUrl()+"/new\" methd=\"get\"><div class=\"form__input-container\" ><input type=\"text\" name=\"value\" value=\""             +  mainValue.get(mainPossion.indexOf(usedPossion.get(k)))  +"\" class=\"form__input\"><input type=\"hidden\" name=\"pos\" value=\""+usedPossion.get(k)+"\"><input type=\"hidden\" name=\"row\" value=\""+String.valueOf(RowCounter) +"\"><div class=\"form__btn-container\"><button type=\"button\" class=\"form__btn\"><img class=\"form__btn-icon\" src=\""+ getUrl()+"/PagesRes/TableDisplay/cancel.svg\" alt=\"\"></button><button type=\"submit\" class=\"form__btn\"><img class=\"form__btn-icon\" src=\""+ getUrl()+"/PagesRes/TableDisplay/checked.svg\" alt=\"\"></button></div></div></form>";
                            table.getSheetList().get(i).getRowList().get(h).setWritten();
                            }else{
                                TableBody = TableBody + mainValue.get(mainPossion.indexOf(usedPossion.get(k)));
                            }                             
                            table.getSheetList().get(i).getRowList().get(h).setWritten();
                            TableBody = TableBody + "</td>";
                        }
                    }
                    catch(Exception e){
                    }
                    //sizeSell=0;        
                }                                                                                                                                                                                                             
                TableBody = TableBody + "</tr>";
                RowCounter++;
                mainPossion.clear();
                mainValue.clear();
                heightPossion.clear();
                listH.clear();
        }
        RowCounter=0;
    }
    TableBody = TableBody + "</table>";                                                                                                
    return TableBody;
}

public static String  tableFormaterSec(Table table, boolean canWrite){
    ArrayList<String> mainPossion = new ArrayList<>();
    ArrayList<String> mainValue = new ArrayList<>();
    ArrayList<String> heightPossion = new ArrayList<>();
    ArrayList<String> HeightList = new ArrayList<>();
    int RowCounter = 0;
    for(int i=0;i<=table.getMax();i++){      
        HeightList.add("1");
    }    
    String TableBody ="<form action=\""+ getUrl()+"/Form\" method=\"post\"><table>"+"<table>";
    for(int i =0 ; i< table.getSheetList().size();i++){
        TableBody = TableBody +"<td>#</td><td>#</td><td rowspan='1' colspan='"+ String.valueOf(table.getMax())+ "' >"+ table.getSheetList().get(i).getName()+"</td>";              
        for(int h =0 ; h< table.getSheetList().get(i).getRowList().size();h++){
            TableBody = TableBody + "<tr><td>"+ table.getSheetList().get(i).getRowList().get(h).getId() +"</td>"+"<td>"+ "<input type=\"checkbox\"  value=\""+i+"-"+String.valueOf(table.getSheetList().get(i).getRowList().get(h).getId())+"\" name=\"rows\">" +"</td>";
                for(int j =0 ; j< table.getSheetList().get(i).getRowList().get(h).getCellList().size();j++){
                    System.out.println();
                    mainPossion.add(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getPossion());
                    mainValue.add(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getValue());
                    heightPossion.add(table.getSheetList().get(i).getRowList().get(h).getCellList().get(j).getHieght());                 
                }                                                                             
                




                //SET HEIGHT TO GLOBAL//
                for(int v=0;v<heightPossion.size();v++){
                    if(Integer.parseInt(heightPossion.get(v))>1){                    
                    HeightList.set(v,String.valueOf(Integer.parseInt(heightPossion.get(v))+1));
                    }                   
                }   
                //GET HIEGHT HELPER//       
                ArrayList<String> listH = new ArrayList<>();    
                for(int v=0;v<HeightList.size();v++){
                    listH.add(HeightList.get(v));    
                }        
                //ALGORITHM HIEGHT - 1
                for(int v=0;v<HeightList.size();v++){
                    if(Integer.parseInt(HeightList.get(v))>1){
                        HeightList.set(v,String.valueOf(Integer.parseInt(HeightList.get(v))-1));
                    }
                }                                                                                 
                ArrayList<String> usedPossion = new ArrayList<String>();              
                //usedPossion FORMATER//
                for(int v=0;v<mainPossion.size();v++){
                    if(v>0 ){
                        if(!mainPossion.get(v).equals(mainPossion.get(v-1))){
                            usedPossion.add(mainPossion.get(v));
                        }
                    }else{
                        usedPossion.add(mainPossion.get(v));
                    }
                }  
                //ADD MAX VALUE OF SELLS IN ROW TO usedPossion//
                usedPossion.add(String.valueOf(table.getMax()));                                                                                                                        
                //SIZE///  
                ArrayList<String> Pos = new ArrayList<String>();               
                for(int v=0;v<mainPossion.size();v++){ 
                    Pos.add( mainPossion.get(v));
                }                    
                ArrayList<String> sizePos = new ArrayList<String>();
                int size = 0;          
                for(int v=0;v<usedPossion.size();v++){ 
                    for(int q=0;q<Pos.size();q++){
                        if(Pos.get(q).equals(usedPossion.get(v))){
                            Pos.set(q,"skipp");
                            size++; 
                        } 
                        else if(Pos.get(q).equals("skipp")){}
                        else{
                            break;                                                           
                        }                                  
                    }  
                    if(size == 0){sizePos.add(String.valueOf(1));}else{sizePos.add(String.valueOf(size));}                    
                    size = 0;    
                }
                // <form action="<%=request.getContextPath()%>/new" methd="get"><input type="text" name="value" value="<%=data.getName()%>"><input type="hidden" name="pos" value="<%=data.getName()%>"><input type="hidden" name="row" value="<%=data.getName()%>"><input type="submit" value="update data" ></form>                                                                                                                                
                //STRING FORMATER//    
                for(int k=0;k<usedPossion.size();k++){                  
                    try{                        
                        if(mainValue.get(mainPossion.indexOf(usedPossion.get(k))).equals("none")){
                            if(mainValue.get(0).equals("null")||mainValue.get(0).equals("")||mainValue.get(0).equals("none")){                
                            } else{
                                if(    mainValue.get(mainPossion.indexOf(usedPossion.get(usedPossion.size()-1))).equals("none")         ){}else{
                                TableBody = TableBody + "<td rowspan='"+heightPossion.get(mainPossion.indexOf(usedPossion.get(k))) +"' colspan='"+sizePos.get(k)+"' class=\""+sizePos.get(k)+"__"+usedPossion.get(k)+"\">"+ ""  +"</td>";                
                                table.getSheetList().get(i).getRowList().get(h).setWritten();
                                }
                            }                            
                            //tableRow = tableRow + "<td rowspan='"+heightPossion.get(mainPossion.indexOf(usedPossion.get(i))) +"' colspan='"+sizeSell+"' style=\" width:"+String.valueOf(50*sizeSell)+"px;\"class=\""+sizeSell+"__"+usedPossion.get(i)+"\">"+  mainValue.get(mainPossion.indexOf(usedPossion.get(i)))  +"</td>";                                            
                        }
                        else if(mainValue.get(mainPossion.indexOf(usedPossion.get(k))).equals("null")||mainValue.get(mainPossion.indexOf(usedPossion.get(k))).equals("")||mainValue.get(mainPossion.indexOf(usedPossion.get(k))).equals("none")){
                            if(mainValue.get(0).equals("null")||mainValue.get(0).equals("")||mainValue.get(0).equals("none")){                
                            }                
                            else if(Integer.parseInt(listH.get(mainPossion.indexOf(usedPossion.get(k))))== 1){
                                TableBody = TableBody + "<td rowspan='"+heightPossion.get(mainPossion.indexOf(usedPossion.get(k))) +"' colspan='"+sizePos.get(k)+"' class=\""+sizePos.get(k)+"__"+usedPossion.get(k)+"\">         ";
                                table.getSheetList().get(i).getRowList().get(h).setWritten();
                                if(canWrite){
                                    //TableBody = TableBody + "<form action=\""+ getUrl()+"/new\" methd=\"get\"><input type=\"text\" name=\"value\" value=\""+ mainValue.get(mainPossion.indexOf(usedPossion.get(k))) +"\"><input type=\"hidden\" name=\"pos\" value=\""+usedPossion.get(k)+"\"><input type=\"hidden\" name=\"row\" value=\""+String.valueOf(RowCounter) +"\"><input type=\"submit\" value=\"update data\" ></form>";                              
                                    TableBody = TableBody +  "<form class=\"form\" action=\""+ getUrl()+"/new\" methd=\"get\"><div class=\"form__input-container\"><input type=\"text\" name=\"value\" value=\""             +  mainValue.get(mainPossion.indexOf(usedPossion.get(k)))  +"\" class=\"form__input\"><input type=\"hidden\" name=\"pos\" value=\""+usedPossion.get(k)+"\"><input type=\"hidden\" name=\"row\" value=\""+String.valueOf(RowCounter) +"\"><div class=\"form__btn-container\"><button type=\"button\" class=\"form__btn\"><img class=\"form__btn-icon\" src=\""+ getUrl()+"/PagesRes/TableDisplay/cancel.svg\" alt=\"\"></button><button type=\"submit\" class=\"form__btn\"><img class=\"form__btn-icon\" src=\""+ getUrl()+"/PagesRes/TableDisplay/checked.svg\" alt=\"\"></button></div></div></form>";
                                    table.getSheetList().get(i).getRowList().get(h).setWritten();
                                }
                                else{
                                    TableBody = TableBody + mainValue.get(mainPossion.indexOf(usedPossion.get(k)));
                                }                                                                                                                
                                table.getSheetList().get(i).getRowList().get(h).setWritten();
                                TableBody = TableBody +  "</td>";                
                            }
                            else{
                            }            
                        }            
                        else{                            
                            TableBody = TableBody + "<td rowspan='"+heightPossion.get(mainPossion.indexOf(usedPossion.get(k))) +"' colspan='"+sizePos.get(k)+"' class=\""+sizePos.get(k)+"__"+usedPossion.get(k)+"\">";
                            table.getSheetList().get(i).getRowList().get(h).setWritten();
                            if(canWrite){
                                //TableBody = TableBody + " <form action=\""+ getUrl()+"/new\" methd=\"get\"><input type=\"text\" name=\"value\" value=\""             +  mainValue.get(mainPossion.indexOf(usedPossion.get(k)))  +"\"><input type=\"hidden\" name=\"pos\" value=\""+usedPossion.get(k)+"\"><input type=\"hidden\" name=\"row\" value=\""+String.valueOf(RowCounter) +"\"><input type=\"submit\" value=\"update data\" ></form>";
                            TableBody = TableBody +  "<form class=\"form\" action=\""+ getUrl()+"/new\" methd=\"get\"><div class=\"form__input-container\" ><input type=\"text\" name=\"value\" value=\""             +  mainValue.get(mainPossion.indexOf(usedPossion.get(k)))  +"\" class=\"form__input\"><input type=\"hidden\" name=\"pos\" value=\""+usedPossion.get(k)+"\"><input type=\"hidden\" name=\"row\" value=\""+String.valueOf(RowCounter) +"\"><div class=\"form__btn-container\"><button type=\"button\" class=\"form__btn\"><img class=\"form__btn-icon\" src=\""+ getUrl()+"/PagesRes/TableDisplay/cancel.svg\" alt=\"\"></button><button type=\"submit\" class=\"form__btn\"><img class=\"form__btn-icon\" src=\""+ getUrl()+"/PagesRes/TableDisplay/checked.svg\" alt=\"\"></button></div></div></form>";
                            table.getSheetList().get(i).getRowList().get(h).setWritten();
                            }else{
                                TableBody = TableBody + mainValue.get(mainPossion.indexOf(usedPossion.get(k)));
                            }                             
                            table.getSheetList().get(i).getRowList().get(h).setWritten();
                            TableBody = TableBody + "</td>";
                        }
                    }
                    catch(Exception e){
                    }
                    //sizeSell=0;        
                }                                                                                                                                                                                                             
                TableBody = TableBody + "</tr>";
                RowCounter++;
                mainPossion.clear();
                mainValue.clear();
                heightPossion.clear();
                listH.clear();
        }
        RowCounter=0;
    }
    TableBody = TableBody + "</table> <input type=\"submit\" value=\"ok\"></form>";                                                                                                
    return TableBody;
}


}



    


    
   

