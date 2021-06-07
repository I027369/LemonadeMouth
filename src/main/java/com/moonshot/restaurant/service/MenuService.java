package com.moonshot.restaurant.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.moonshot.restaurant.api.model.APIMenu;
import com.moonshot.restaurant.api.model.APIMenuDetails;
import com.moonshot.restaurant.api.model.APIMenuItem;
import com.moonshot.restaurant.api.model.APIMenuWithItemAndCategory;
import com.moonshot.restaurant.entity.Menu;
import com.moonshot.restaurant.entity.MenuItem;
import com.moonshot.restaurant.entity.MenuItemCategory;
import com.moonshot.restaurant.entity.Restaurant;
import com.moonshot.restaurant.entity.RestaurantTable;
import com.moonshot.restaurant.repository.MenuItemCategoryRepository;
import com.moonshot.restaurant.repository.MenuItemRepository;
import com.moonshot.restaurant.repository.MenuRepository;
import com.moonshot.restaurant.repository.RestaurantRepository;
import com.moonshot.restaurant.repository.RestaurantTableRepository;

@Service
public class MenuService {
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private MenuItemRepository menuItemRepository;
	@Autowired
	private MenuItemCategoryRepository menuItemCategoryRepository;
	@Autowired
	private MenuItemService menuItemService;

	public List<APIMenu> getAllMenu(Long restaurantId) {
		// TODO Auto-generated method stub
		 List<Menu> menuList = menuRepository.findByRestaurantId(restaurantId);
		 if (menuList != null){
			 Menu menu = null;
			 APIMenu apiMenu = null;
			 List<APIMenu> apiMenuList = new ArrayList<>();
			 Iterator<Menu> menuListIterator = menuList.iterator();
			 while(menuListIterator.hasNext()){
				 menu = menuListIterator.next();
				 apiMenu = new APIMenu();
				 apiMenu = copyMenu(menu, apiMenu);
				 apiMenuList.add(apiMenu);				 
			 }
			 return apiMenuList;
		 }
		 return null;
	}


	public APIMenuDetails getMenu(Long menuId) throws WriterException{
		// TODO Auto-generated method stub
		APIMenuDetails apiMenuDetails = new APIMenuDetails();
		Menu menu = menuRepository.findOne(menuId);
		if (menu != null){
			apiMenuDetails = (APIMenuDetails)copyMenu(menu, apiMenuDetails);
			apiMenuDetails.setMenuItem(menuItemService.getAllMenuItems(apiMenuDetails.getId()));
			return apiMenuDetails;
		}else {
			throw new WriterException("Menu not found");
		}
	}
	

	public APIMenu addMenu(APIMenu apiMenu) throws WriterException, IOException{
		// TODO Auto-generated method stub
		Menu menu = copyAPIMenu(new Menu(), apiMenu);
		if (menu.getAvailableFrom() == null)
			throw new WriterException ("Avaialble-From is empty");
		if (menu.getAvailableTo() == null)
				throw new WriterException ("Available-To is empty");		
		// if(menu.getAvailableFrom().isAfter(menu.getAvailableTo()))
		//	throw new WriterException("Avaialble-From can not be later that Avaialble-To");
		
		menu.setStatus("Draft");		
		menuRepository.save(menu);
		//menu = setMenuItem(menu, apiMenu);
		apiMenu = copyMenu(menu, apiMenu);
		return apiMenu;
	}

	public APIMenu updateMenu(Long menuId, APIMenu apiMenu) throws WriterException, IOException {
		// TODO Auto-generated method stub
		Menu menu = menuRepository.findOne(menuId);
		if (menu == null)
			throw new WriterException("Menu not found");
		Long tempId = menu.getId();
		menu = copyAPIMenu(menu, apiMenu);
		menu.setId(tempId);
	//	if(menu.getAvailableFrom().isAfter(menu.getAvailableTo()))
	//		throw new WriterException("Avaialble From can not be later that Avaialble To");
		menuRepository.save(menu);
		apiMenu = copyMenu(menu, apiMenu);
		return apiMenu;
	}
	

	public APIMenu updateMenuStatus(Long menuid, String localStatus) throws WriterException {
		// TODO Auto-generated method stub
		Menu menu = menuRepository.findOne(menuid);
		if (menu == null)
			throw new WriterException("Menu not found");		
		menu.setStatus(localStatus);;
		menuRepository.save(menu);
		return copyMenu(menu, new APIMenu());
		
	}
	

	public void deleteMenu(Long menuId) throws WriterException{
		// TODO Auto-generated method stub
		Menu menu = menuRepository.findOne(menuId);
		if (menu == null)
			throw new WriterException("Menu not found");
		menuRepository.delete(menuId);
		
	}

	public APIMenuDetails addMenuItemToMenu(Long menuid, Long menuitemid) throws WriterException{
		// TODO Auto-generated method stub
		Menu menu = menuRepository.findOne(menuid);
		MenuItem menuItem = menuItemRepository.findOne(menuitemid);
		if (menu == null)
			throw new WriterException ("Menu not found");
		if (menuItem == null)
				throw new WriterException ("Menu Item not found");
		//menu.getMenuItem().add(menuItem);
		menuItem.getMenu().add(menu);
		menuItemRepository.save(menuItem);
		
		APIMenuDetails apiMenuDetails = new APIMenuDetails();
		apiMenuDetails = (APIMenuDetails)copyMenu(menu, apiMenuDetails);
		apiMenuDetails.setMenuItem(menuItemService.getAllMenuItems(apiMenuDetails.getId()));
		return apiMenuDetails;		
	}

	

	public APIMenuDetails delMenuItemFromMenu(Long menuid, Long menuitemid) throws WriterException{
		// TODO Auto-generated method stub
		Menu menu = menuRepository.findOne(menuid);
		MenuItem menuItem = menuItemRepository.findOne(menuitemid);
		if (menu == null)
			throw new WriterException ("Menu not found");
		if (menuItem == null)
				throw new WriterException ("Menu Item not found");
		menuItem.getMenu().remove(menu);
		menuItemRepository.save(menuItem);
		APIMenuDetails apiMenuDetails = new APIMenuDetails();
		apiMenuDetails = (APIMenuDetails)copyMenu(menu, apiMenuDetails);
		apiMenuDetails.setMenuItem(menuItemService.getAllMenuItems(apiMenuDetails.getId()));
		return apiMenuDetails;	
	}

	private APIMenu copyMenu(Menu menu, APIMenu apiMenu){
		apiMenu.setId(menu.getId());		
		apiMenu.setName(menu.getName());
		apiMenu.setStatus(menu.getStatus());			
		apiMenu.setAvailableFrom(menu.getAvailableFrom().toString());
		apiMenu.setAvailableTo(menu.getAvailableTo().toString());			
		apiMenu.setRestaurantId(menu.getRestaurant().getId());		
		return apiMenu;
	}
	private Menu copyAPIMenu(Menu menu, APIMenu apiMenu){		
		
		if(apiMenu.getName() != null)
			menu.setName(apiMenu.getName());		
		if(apiMenu.getAvailableFrom() != null)
			menu.setAvailableFrom(OffsetTime.parse(apiMenu.getAvailableFrom()));	
		if(apiMenu.getAvailableTo() != null)
			menu.setAvailableTo(OffsetTime.parse(apiMenu.getAvailableTo()));	
		if(apiMenu.getRestaurantId() != null)
			menu.setRestaurant(restaurantRepository.findOne(apiMenu.getRestaurantId()));	
		
		
		return menu;
	}
	
	
	public List<APIMenuWithItemAndCategory> getAvailableMenu(Long restaurantId) throws WriterException, ParseException{
		// TODO Auto-generated method stub
		List<APIMenuWithItemAndCategory> apiMenuwithItemAndCatList = new ArrayList<APIMenuWithItemAndCategory>();
		
		Date d1 = new Date();
	    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	    format.setTimeZone(TimeZone.getTimeZone("GMT"));
	    String currentTime = format.format(d1);		
		OffsetTime tempTime = OffsetTime.now().withOffsetSameInstant(ZoneOffset.ofHours(0));
		
		
		List<Menu> menuList = menuRepository.findByRestaurantId(restaurantId);
		
		if (menuList.isEmpty())
			throw new WriterException("No menus found");
		List<APIMenu> availableMenuList = new ArrayList<>();
		Menu menu = null;
		Iterator<Menu> menuListIterator = menuList.iterator();
		 while(menuListIterator.hasNext()){
			 menu = menuListIterator.next();
			 OffsetTime from = menu.getAvailableFrom().withOffsetSameInstant(ZoneOffset.ofHours(0));
			 OffsetTime to = menu.getAvailableTo().withOffsetSameInstant(ZoneOffset.ofHours(0));
			 if(menu.getStatus().equalsIgnoreCase("Published")){
				 if (	from.compareTo(tempTime) == 0
						|| to.compareTo(tempTime) == 0
					){
					 
					 availableMenuList.add(copyMenu(menu, new APIMenu()));
				 }
				 if (isTimeBetweenTwoTime(
						 from.format(DateTimeFormatter.ISO_LOCAL_TIME), 
						 to.format(DateTimeFormatter.ISO_LOCAL_TIME),
						 currentTime
							)
					){
						 
					 availableMenuList.add(copyMenu(menu, new APIMenu()));
				}
			 }
		 }
		 
		 if(!availableMenuList.isEmpty()){
			 Hashtable<Long, List<APIMenuItem>> hashTable = new Hashtable<Long, List<APIMenuItem>>();
			 Iterator<APIMenu> localAPIMenuList = availableMenuList.iterator();
			 APIMenu localAPIMenu = null;
			 while(localAPIMenuList.hasNext()){
				 localAPIMenu = localAPIMenuList.next();
				 List<APIMenuItem> activeMenuItems = menuItemService.getActiveMenuItems(localAPIMenu.getId());
				 Iterator<APIMenuItem> localAPIMenuItemList = activeMenuItems.iterator();
				 APIMenuItem localAPIMenuItem = null;
				 List<APIMenuItem> listLocalAPIMenuItem = null;
				 while(localAPIMenuItemList.hasNext()){
					 localAPIMenuItem = localAPIMenuItemList.next();
					 listLocalAPIMenuItem = hashTable.get(localAPIMenuItem.getCategoryId());
					 if(listLocalAPIMenuItem == null){
						 listLocalAPIMenuItem = new ArrayList<APIMenuItem>();
						 hashTable.put(localAPIMenuItem.getCategoryId(), listLocalAPIMenuItem);
					 }
					 listLocalAPIMenuItem.add(localAPIMenuItem);					 
				 }
			 }	
			 
			 Set<Long> keys = hashTable.keySet();
			 MenuItemCategory menuItemCategory = null;
			 APIMenuWithItemAndCategory apiMenuWithIAndC = null;
		        for(Long key: keys){
		        	menuItemCategory = menuItemCategoryRepository.findOne(key);
		        	apiMenuWithIAndC = new APIMenuWithItemAndCategory();
		        	apiMenuWithIAndC.setId(key);
		        	apiMenuWithIAndC.setName(menuItemCategory.getName());
		        	apiMenuWithIAndC.setSortOrder(menuItemCategory.getSortOrder());
		        	apiMenuWithIAndC.setMenuItem(hashTable.get(key));	
		        	apiMenuwithItemAndCatList.add(apiMenuWithIAndC);
		        }
		        apiMenuwithItemAndCatList.sort(Comparator.comparing(APIMenuWithItemAndCategory::getSortOrder));    
		 }		  
		
		return apiMenuwithItemAndCatList;
	}

	public boolean isTimeBetweenTwoTime(String argStartTime,
            String argEndTime, String argCurrentTime) throws ParseException {
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        //
        argStartTime = argStartTime.substring(0,8);
        argEndTime = argEndTime.substring(0,8);
        if (argStartTime.matches(reg) && argEndTime.matches(reg)
                && argCurrentTime.matches(reg)) {
            boolean valid = false;
            // Start Time
            java.util.Date startTime = new SimpleDateFormat("HH:mm:ss")
                    .parse(argStartTime);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startTime);

            // Current Time
            java.util.Date currentTime = new SimpleDateFormat("HH:mm:ss")
                    .parse(argCurrentTime);
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(currentTime);

            // End Time
            java.util.Date endTime = new SimpleDateFormat("HH:mm:ss")
                    .parse(argEndTime);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endTime);

            //
            if (currentTime.compareTo(endTime) < 0) {

                currentCalendar.add(Calendar.DATE, 1);
                currentTime = currentCalendar.getTime();

            }

            if (startTime.compareTo(endTime) < 0) {

                startCalendar.add(Calendar.DATE, 1);
                startTime = startCalendar.getTime();

            }
            //
            if (currentTime.before(startTime)) {

                //System.out.println(" Time is Lesser ");

                valid = false;
            } else {

                if (currentTime.after(endTime)) {
                    endCalendar.add(Calendar.DATE, 1);
                    endTime = endCalendar.getTime();

                }

                //System.out.println("Comparing , Start Time /n " + startTime);
                //System.out.println("Comparing , End Time /n " + endTime);
                //System.out.println("Comparing , Current Time /n " + currentTime);

                if (currentTime.before(endTime)) {
                    //System.out.println("RESULT, Time lies b/w");
                    valid = true;
                } else {
                    valid = false;
                    //System.out.println("RESULT, Time does not lies b/w");
                }

            }
            return valid;

        } else {
            throw new IllegalArgumentException(
                    "Not a valid time, expecting HH:MM:SS format");
        }

    }

	
}
