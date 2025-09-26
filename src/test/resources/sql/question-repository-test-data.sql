INSERT INTO question (id, question, explanation)
VALUES (1, '1번 문제 입니다.', '1번 문제에 대한 설명입니다.'),
       (2, '2번 문제 입니다.', '2번 문제에 대한 설명입니다.'),
       (3, '3번 문제 입니다.', '3번 문제에 대한 설명입니다.');

INSERT INTO choice (id, number, text, is_correct, question_id)
VALUES (1, 'A', 'A번 보기입니다.', false, 1),
       (2, 'B', 'B번 보기입니다.', false, 1),
       (3, 'C', 'C번 보기입니다.', true, 1),
       (4, 'D', 'D번 보기입니다.', false, 1),
       (5, 'A', 'A번 보기입니다.', false, 2),
       (6, 'B', 'B번 보기입니다.', true, 2),
       (7, 'C', 'C번 보기입니다.', false, 2),
       (8, 'D', 'D번 보기입니다.', false, 2),
       (9, 'A', 'A번 보기입니다.', false, 3),
       (10, 'B', 'B번 보기입니다.', true, 3),
       (11, 'C', 'C번 보기입니다.', false, 3),
       (12, 'D', 'D번 보기입니다.', false, 3);