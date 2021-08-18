package co.kr.petopia.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.kr.petopia.service.CartService;
import co.kr.petopia.service.MemberService;
import co.kr.petopia.service.OrderService;
import co.kr.petopia.vo.CartVO;
import co.kr.petopia.vo.MemberVO;
import co.kr.petopia.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private CartService cartService;
    
	// 회원
	// 회원 주문서 작성(회원일 경우 정보 불러오기)
	@GetMapping("/member/checkout")
	public String getMemberOrderForm(Model model, Principal principal, HttpSession session) {
		log.info("getOrderForm()..");
		log.info("OrderService.getCartList()..");

		MemberVO memberInfo;
	
		if (principal != null) {
			String member_id = principal.getName();
			List<CartVO> cartInfo = cartService.goCart(member_id);
			log.info("카트정보: " + cartInfo);
			
			memberInfo = memberService.getMemberInfo(member_id);
			
			model.addAttribute("memberInfo", memberInfo);
			model.addAttribute("order", orderService.getMemberCartList(member_id));
			log.info(member_id);
			
		}
		
		

		return "/order/checkout_member";
	}

	// 회원 주문 상세 페이지(무통장입금)
	@GetMapping("/member/confirmation_deposit")
	public String confirmation_deposit(OrderVO orderVO, Model model, Principal principal, HttpSession httpSession) {
		log.info("confirmation_deposit");
		log.info("orderService.readOrderInfo()..");
		
		String member_id = principal.getName();
		log.info("member_id = " + member_id);
		
		List<CartVO> cartInfo = cartService.goCart(member_id);
		log.info("카트정보: " + cartInfo);
		
		log.info("orderVO = " + orderService.readMemberOrderInfo());
		model.addAttribute("confirmation_deposit", orderService.readMemberOrderInfo());
		model.addAttribute("order", orderService.getMemberCartList(member_id));
		
		

		return "/order/confirmation_deposit";
	}

	// 회원 주문 상세 페이지(카드)
	@GetMapping("/member/confirmation_card")
	public String confirmation_card(OrderVO orderVO, Model model, Principal principal, HttpSession httpSession) {
		log.info("confirmation_card");
		log.info("orderService.readOrderInfo()..");

		String member_id = principal.getName();

		model.addAttribute("confirmation_card", orderService.readMemberOrderInfo());
		model.addAttribute("order", orderService.getMemberCartList(member_id));

		return "/order/confirmation_card";
	}

	// 회원 주문자 정보 입력 데이터 넘기기
	@PostMapping("/member/order-proc")
	@ResponseBody
	public String orderInsert( OrderVO orderVO, RedirectAttributes rttr, Principal principal) {
		log.info("주문입력 : " + orderVO);

		String member_id = principal.getName();

		orderService.memberOrderFormInsert(member_id, orderVO.getProduct_idx());

		orderVO.setOrder_idx(orderService.getOrderFormCurrVal());

		orderService.memberOrderDetailInsert(
								orderVO.getOrder_idx(), orderVO.getProduct_idx(),
								orderVO.getOrder_name(), orderVO.getOrder_receiver_name(),
							    orderVO.getOrder_receiver_phonenumber(), orderVO.getOrder_receiver_address(),
								orderVO.getOrder_quantity(), orderVO.getPayment_method(), orderVO.getOrder_date());

		rttr.addFlashAttribute("result", "success");

		orderService.insertPoint(orderVO);
		orderService.updatePoint(orderVO);
		
		return "success";
	}

	
	// 비회원
	// 비회원 주문서 작성(장바구니 리스트 불러오기)
	@GetMapping("/users/checkout")
	public String getOrderForm(Model model, HttpSession session) {
		log.info("getOrderForm()..");

		return "/order/checkout_users";
	}

	// 비회원 주문 상세 페이지(무통장입금)
	@GetMapping("/users/confirmation_deposit")
	public String confirmation_deposit(OrderVO orderVO, Model model, HttpSession session) {
		log.info("confirmation_deposit");
		log.info("orderService.readOrderInfo()..");

		model.addAttribute("confirmation_deposit", orderService.readOrderInfo());

		return "/order/confirmation_deposit";
	}

	// 비회원 주문 상세 페이지(카드)
	@GetMapping("/users/confirmation_card")
	public String confirmation_card(OrderVO orderVO, Model model, HttpSession session) {
		log.info("confirmation_card");
		log.info("orderService.readOrderInfo()..");

		model.addAttribute("confirmation_deposit", orderService.readOrderInfo());

		return "/order/confirmation_card";
	}

	// 비회원 주문자 정보 입력 데이터 넘기기
	@PostMapping("/users/order-proc")
	@ResponseBody
	public String orderInsert(OrderVO orderVO, RedirectAttributes rttr) {
		log.info("order : " + orderVO);

		orderService.orderFormInsert(orderVO);

		orderVO.setOrder_idx(orderService.getOrderFormCurrVal());

		orderService.orderDetailInsert(orderVO);

		rttr.addFlashAttribute("result", "success");

		return "success";
	}

}
