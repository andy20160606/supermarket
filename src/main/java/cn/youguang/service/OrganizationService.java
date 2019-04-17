package cn.youguang.service;

import cn.youguang.entity.Organization;
import cn.youguang.util.Tree;
import cn.youguang.repository.OrganizationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrganizationService {
	@Autowired
	private OrganizationDao organizationDao;

	public void saveOrg(Organization organization) {
		organizationDao.save(organization);
	}

	public Organization findOrgById(Long id) {
		return organizationDao.findOne(id);
	}

	public void delOrgById(long id) {
		organizationDao.delete(id);
	}

	public List<Tree> findTree() {
		List<Tree> trees = new ArrayList<Tree>();

		List<Organization> organizationFather = organizationDao.findByParentIsNull();

		if (organizationFather != null) {
			for (Organization organizationOne : organizationFather) {
				Tree treeOne = new Tree();

				treeOne.setId(organizationOne.getId());
				treeOne.setText(organizationOne.getName());
				treeOne.setIconCls(organizationOne.getIcon());

				List<Organization> organizationSon = organizationOne.getChildren();

				if (organizationSon != null) {
					List<Tree> treeSon = new ArrayList<Tree>();
					for (Organization organizationTwo : organizationSon) {
						Tree treeTwo = new Tree();
						treeTwo.setId(organizationTwo.getId());
						treeTwo.setText(organizationTwo.getName());
						treeTwo.setIconCls(organizationTwo.getIcon());
						treeSon.add(treeTwo);

						List<Organization> organizationGrandSon = organizationTwo.getChildren();
						if (organizationGrandSon != null) {
							List<Tree> treeGrandSon = new ArrayList<Tree>();
							for (Organization organizationThree : organizationGrandSon) {
								Tree treeThree = new Tree();
								treeThree.setId(organizationThree.getId());
								treeThree.setText(organizationThree.getName());
								treeThree.setIconCls(organizationThree.getIcon());
								treeGrandSon.add(treeThree);
							}
							treeTwo.setChildren(treeGrandSon);
						} else
							treeTwo.setState("closed");
					}
					treeOne.setChildren(treeSon);
				} else {
					treeOne.setState("closed");
				}
				trees.add(treeOne);
			}
		}
		return trees;
	}

	public List<Organization> findOrgTop() {
		List<Organization> list = new ArrayList<Organization>();
		for (Organization organization : organizationDao.findByParentIsNull()) {
			list.add(organization);
		}
		return list;
	}

	public void addOrganization(Organization organization) {
		organizationDao.save(organization);

	}

	public Organization findOrganizationById(Long id) {
		return organizationDao.findOne(id);
	}

	public void updateOrganization(Organization organization) {
		organizationDao.save(organization);

	}

	public void deleteOrganizationById(Long id) {
		organizationDao.delete(id);

	}

    public List<Organization> findAll() {
		return organizationDao.findAll();
    }
}
