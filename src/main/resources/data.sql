-- 캠페인 샘플 데이터
INSERT INTO campaign (id, title, description, category, product_name, application_deadline, review_deadline, quota, recruit_active, review_active)
VALUES
    (1, '여름 필수템 선풍기 체험단', '무더위를 날릴 강력한 선풍기 체험단 모집!', '가전', '썸머윈드 선풍기', '2025-05-01', '2025-05-15', 30, true, false),
    (2, '고급 샴푸 체험단', '프리미엄 헤어케어 제품을 직접 사용해보세요!', '뷰티', '럭셔리샴푸', '2025-05-10', '2025-05-25', 50, true, false);

-- 신청 샘플 데이터
INSERT INTO application (id, campaign_id, reviewer_id, reviewer_nickname, phone, sns_url, text, status)
VALUES
    (1, 1, 101, 'reviewer_yo', '010-1234-5678', 'https://instagram.com/reviewer_yo', '꼭 써보고 싶어요! 여름에 잘 어울릴 것 같아요.', '신청대기'),
    (2, 2, 102, 'shampoo_lover', '010-2345-6789', 'https://blog.naver.com/shampoo_lover', '머릿결 관리에 진심입니다. 잘 써볼게요!', '신청대기');
