package mo.service.impl;

import mo.dao.ContestApplyMapper;
import mo.dao.ContestMapper;
import mo.entity.po.ContestApply;
import mo.entity.vo.link.ContestApplyLink;
import mo.service.ContestApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContestApplyServiceImpl implements ContestApplyService {

    @Resource
    private ContestMapper contestMapper;

    @Resource
    private ContestApplyMapper contestApplyMapper;

    @Override
    public List<ContestApplyLink> getContestApplyByContestId(Integer contestId, int page, int per_page) {
        return makeLink(contestApplyMapper.getContestApplyByCreatorId(contestId, (page - 1) * per_page, per_page));
    }

    private List<ContestApplyLink> makeLink(List<ContestApply> contestApplies) {
        List<ContestApplyLink> contestApplyLinks = new ArrayList<>(contestApplies.size() + 3);
        for (ContestApply item : contestApplies) {
            contestApplyLinks.add(new ContestApplyLink(item, contestMapper.findContestByContestId(item.getContest_id())));
        }
        return contestApplyLinks;
    }
}
