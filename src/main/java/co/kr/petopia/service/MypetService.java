package co.kr.petopia.service;

import java.util.List;

import co.kr.petopia.vo.MypetVO;

public interface MypetService {

    // 목록
    List<MypetVO> getMypetList();
    
    // 등록
    void mypetRegister(MypetVO mypet);
    
    // 수정
    int mypetModify(MypetVO mypet);
    
    // 삭제
    int mypetRemove(int mypet_idx);
    

}
    

