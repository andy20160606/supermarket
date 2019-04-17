package cn.youguang.service;

import cn.youguang.entity.Resource;
import cn.youguang.entity.User;
import cn.youguang.util.Config;
import cn.youguang.util.Tree;
import cn.youguang.repository.ResourceDao;
import cn.youguang.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ResourceService {
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private UserDao userDao;

	public void saveRes(Resource resource) {
		resourceDao.save(resource);
	}

	public Resource findResById(Long id) {
		return resourceDao.findOne(id);
	}

	public void delResById(long id) {
		resourceDao.delete(id);
	}

	public List<Tree> findTree(User user) {
		List<Tree> trees = new ArrayList<Tree>();
		// 超级管理

//		if (user.getLoginname().equals("admin")) {
			List<Resource> resFather = resourceDao.findByParentIsNullAndResourcetype(Config.RESOURCE_MENU);
			if (resFather == null) {
				return null;
			}

			for (Resource resourceOne : resFather) {
				Tree treeOne = new Tree();

				treeOne.setId(resourceOne.getId());
				treeOne.setText(resourceOne.getName());
				treeOne.setIconCls(resourceOne.getIcon());
				treeOne.setAttributes(resourceOne.getUrl());
				List<Resource> resourceSon = resourceOne.getChildren();

				if (resourceSon != null) {
					List<Tree> tree = new ArrayList<Tree>();
					for (Resource resourceTwo : resourceSon) {
						if (Config.RESOURCE_MENU == resourceTwo.getResourcetype()) {
							Tree treeTwo = new Tree();
							treeTwo.setId(resourceTwo.getId());
							treeTwo.setText(resourceTwo.getName());
							treeTwo.setIconCls(resourceTwo.getIcon());
							treeTwo.setAttributes(resourceTwo.getUrl());
							tree.add(treeTwo);
						}
					}
					treeOne.setChildren(tree);
				} else {
					treeOne.setState("closed");
				}
				trees.add(treeOne);
			}
			return trees;
		//}

//		// 普通用户
//		User currentUser = userDao.findById(user.getId());
//		if (currentUser == null)
//			return null;
//		for (Role role : currentUser.getRoles()) {
//			for (Resource resource : role.getRess()) {
//				if (resource != null && resource.getParent() == null) {
//					Tree treeOne = new Tree();
//					treeOne.setId(resource.getId());
//					treeOne.setText(resource.getName());
//					treeOne.setIconCls(resource.getIcon());
//					treeOne.setAttributes(resource.getUrl());
//					List<Tree> tree = new ArrayList<Tree>();
//					for (Resource resourceTwo : resource.getChildren()) {
//						if (Config.RESOURCE_MENU == resourceTwo.getResourcetype()) {
//							Tree treeTwo = new Tree();
//							treeTwo.setId(resourceTwo.getId());
//							treeTwo.setText(resourceTwo.getName());
//							treeTwo.setIconCls(resourceTwo.getIcon());
//							treeTwo.setAttributes(resourceTwo.getUrl());
//							tree.add(treeTwo);
//						}
//					}
//					treeOne.setChildren(tree);
//					trees.add(treeOne);
//				}
//			}
//		}
//		return trees;

	}

	public void deleteResById(Long id) {
		resourceDao.delete(id);
		
	}

	public void updateRes(Resource resource) {
		resourceDao.save(resource);
		
	}

	/**
	 * 三级资源树
	 * @return
	 */
	public List<Tree> findAllTrees() {
		System.out.println("三级资源树");
        List<Tree> treeOneList = new ArrayList<Tree>();

        // 查询所有的一级树
        List<Resource> resources = resourceDao.findByParentIsNullAndResourcetype(Config.RESOURCE_MENU);
        if (resources == null) {
            return null;
        }

        for (Resource resourceOne : resources) {
            Tree treeOne = new Tree();

            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());

            List<Resource> resourceSon = resourceOne.getChildren();

            if (resourceSon == null || resourceSon.size()==0) {
                treeOne.setState("closed");
            } else {
                List<Tree> treeTwoList = new ArrayList<Tree>();

                for (Resource resourceTwo : resourceSon) {
                    Tree treeTwo = new Tree();

                    treeTwo.setId(resourceTwo.getId());
                    treeTwo.setText(resourceTwo.getName());
                    treeTwo.setIconCls(resourceTwo.getIcon());
                    treeTwo.setAttributes(resourceTwo.getUrl());

                    /***************************************************/
                    List<Resource> resourceSons = resourceTwo.getChildren();

                    if (resourceSons == null|| resourceSons.size()==0) {
                        treeTwo.setState("closed");
                    } else {
                        List<Tree> treeThreeList = new ArrayList<Tree>();

                        for (Resource resourceThree : resourceSons) {
                            Tree treeThree = new Tree();

                            treeThree.setId(resourceThree.getId());
                            treeThree.setText(resourceThree.getName());
                            treeThree.setIconCls(resourceThree.getIcon());
                            treeThree.setAttributes(resourceThree.getUrl());

                            treeThreeList.add(treeThree);
                        }
                        treeTwo.setChildren(treeThreeList);
                    }
                    /***************************************************/

                    treeTwoList.add(treeTwo);
                }
                treeOne.setChildren(treeTwoList);
            }

            treeOneList.add(treeOne);
        }
        return treeOneList;
    }


	/**
	 * 二级资源树
	 * @return
	 */
	public List<Tree> findAllTree() {
		System.out.println("二级资源树");
        List<Tree> treeOneList = new ArrayList<Tree>();

        // 查询所有的一级树
        List<Resource> resources = resourceDao.findByParentIsNullAndResourcetype(Config.RESOURCE_MENU);
        if (resources == null) {
            return null;
        }

        for (Resource resourceOne : resources) {
            Tree treeOne = new Tree();

            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());

            List<Resource> resourceSon = resourceOne.getChildren();

            if (resourceSon == null || resourceSon.size()==0) {
                treeOne.setState("closed");
            } else {
                List<Tree> treeTwoList = new ArrayList<Tree>();

                for (Resource resourceTwo : resourceSon) {
                    Tree treeTwo = new Tree();

                    treeTwo.setId(resourceTwo.getId());
                    treeTwo.setText(resourceTwo.getName());
                    treeTwo.setIconCls(resourceTwo.getIcon());
                    treeTwo.setAttributes(resourceTwo.getUrl());
                    treeTwoList.add(treeTwo);
                }
                treeOne.setChildren(treeTwoList);
            }

            treeOneList.add(treeOne);
        }
        return treeOneList;
	}

	public void addRes(Resource res) {
		resourceDao.save(res);
		
	}

	public List<Resource> findResAll() {
		ArrayList<Resource> list=new ArrayList<Resource>();
		for (Resource res:resourceDao.findAll()){
			list.add(res);
		}
		return list;
	}
	
	public List<Resource> findResTop(){
		ArrayList<Resource> list=new ArrayList<Resource>();
		for (Resource res:resourceDao.findByParentIsNullAndResourcetype(Config.RESOURCE_MENU)){
			list.add(res);
		}
		return list;
		
	}
}
