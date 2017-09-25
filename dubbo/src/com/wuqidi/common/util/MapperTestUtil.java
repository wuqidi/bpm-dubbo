//package com.bonc.ioc.common.util;
//
//import java.util.HashMap;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.bonc.ioc.omp.dao.area.AreaMapper;
//import com.bonc.ioc.omp.dao.res.InterfaceResourceMapper;
//import com.bonc.ioc.omp.dao.sys.SysMenuMapper;
//import com.bonc.ioc.omp.model.area.DicArea;
//import com.bonc.ioc.omp.model.sys.SysMenu;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:/spring-mvc.xml",
//		"classpath:/spring-context.xml"
//						})
//public class MapperTestUtil {
//
////	@Resource
////	private UcNoticeMapper ucNoticeMapper;
////	@Resource
////	private SafeSsoServerMapper safeSsoServerMapper;
////	@Resource
////	private InterfaceResourceMapper interfaceResourceMapper;
////	@Resource
////	private SysMenuMapper sysMenuMapper;
////	@Resource
////	private AreaMapper areaMapper;
//	
//	@Test
//	public void test() throws Exception{ 
////		String noticeCode = "2017[经开区]03020001";
////		UcNotice notice = new UcNotice();
////		notice.setId(null);
////		notice.setNoticeCode(noticeCode);
////		ucNoticeMapper.save(notice);
//		HashMap<String, Object> map = new HashMap<String, Object>();
////		map.put("pageSize", "3");
////		map.put("currentPage", "1" );
////		map.put("orderBy", " interface_resource_browse desc " );
////		ucNoticeMapper.findById(notice.getId());
////		ucNoticeMapper.findByCode(noticeCode);
////		ucNoticeMapper.selectByPage(map);
////		notice.setNoticeDescHtml(noticeCode+new Random().nextInt(1000));
////		ucNoticeMapper.update(notice);
////		ucNoticeMapper.delete(notice.getId());
////		System.out.println(safeSsoServerMapper.getSafeSsoServerList(null).size());
////		System.out.println(safeSsoServerMapper.findById("6a02cb8e91d6d9edfdb7f2d2955c242b").getId()) ;
////		interfaceResourceMapper.findById("1").getId();
////		interfaceResourceMapper.selectByPage(map);
////		map.put("sysId", "1392326694b44465a0607fb87fc4780a");
////		map.put("accId", "1aa0d97929a34fb6b9afbc91891deffa");
////		System.out.println(sysMenuMapper.findMenuList(map).size());
//		map.put("findAll", true);
//		HashMap<String, Object> tempMap = new HashMap<String, Object>();
////		List<SysMenu> list = sysMenuMapper.selectByPage(map);
////		for(SysMenu sysMenu: list){
////			tempMap.put(sysMenu.getMenuCode(), sysMenu.getMenuPcode());
////		}
////		for(SysMenu sysMenu: list){
////			int i = 0;
////			String temp = sysMenu.getMenuCode();
////			while(!StringUtils.isEmpty(temp)){
////				i++;
////				temp = (String) tempMap.get(temp);
////			}
////			sysMenu.setMenuLevel(i);
////			sysMenuMapper.update(sysMenu);
////		}
//		
//		List<DicArea> list = areaMapper.selectByPage(map);
//		for(DicArea dicArea: list){
//			tempMap.put(dicArea.getAreaCode(), dicArea.getAreaPcode());
//		}
//		for(DicArea dicArea: list){
//			int i = 1;
//			String temp = dicArea.getAreaCode();
//			while(!StringUtils.isEmpty(temp)){
//				i++;
//				temp = (String) tempMap.get(temp);
//			}
//			dicArea.setAreaLevel(i);
//			areaMapper.update(dicArea);
//		}
//		
//	}
//}
