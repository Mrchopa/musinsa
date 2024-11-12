-- categories 테이블에 카테고리 데이터 삽입
INSERT INTO categories (name, display_order) VALUES
    ('상의', 1),
    ('아우터', 2),
    ('바지', 3),
    ('스니커즈', 4),
    ('가방', 5),
    ('모자', 6),
    ('양말', 7),
    ('액세서리', 8);
    
-- brands 테이블에 브랜드 데이터 삽입
INSERT INTO brands (name) VALUES
    ('A'), ('B'), ('C'), ('D'), ('E'), ('F'), ('G'), ('H'), ('I');

-- products 테이블에 각 브랜드별 상품 데이터 삽입
INSERT INTO products (brand_id, category_id, price) VALUES
    -- 브랜드 A
    ((SELECT id FROM brands WHERE name = 'A'), (SELECT id FROM categories WHERE name = '상의'), 11200),
    ((SELECT id FROM brands WHERE name = 'A'), (SELECT id FROM categories WHERE name = '아우터'), 5500),
    ((SELECT id FROM brands WHERE name = 'A'), (SELECT id FROM categories WHERE name = '바지'), 4200),
    ((SELECT id FROM brands WHERE name = 'A'), (SELECT id FROM categories WHERE name = '스니커즈'), 9000),
    ((SELECT id FROM brands WHERE name = 'A'), (SELECT id FROM categories WHERE name = '가방'), 2000),
    ((SELECT id FROM brands WHERE name = 'A'), (SELECT id FROM categories WHERE name = '모자'), 1700),
    ((SELECT id FROM brands WHERE name = 'A'), (SELECT id FROM categories WHERE name = '양말'), 1800),
    ((SELECT id FROM brands WHERE name = 'A'), (SELECT id FROM categories WHERE name = '액세서리'), 2300),

    -- 브랜드 B
    ((SELECT id FROM brands WHERE name = 'B'), (SELECT id FROM categories WHERE name = '상의'), 10500),
    ((SELECT id FROM brands WHERE name = 'B'), (SELECT id FROM categories WHERE name = '아우터'), 5900),
    ((SELECT id FROM brands WHERE name = 'B'), (SELECT id FROM categories WHERE name = '바지'), 3800),
    ((SELECT id FROM brands WHERE name = 'B'), (SELECT id FROM categories WHERE name = '스니커즈'), 9100),
    ((SELECT id FROM brands WHERE name = 'B'), (SELECT id FROM categories WHERE name = '가방'), 2100),
    ((SELECT id FROM brands WHERE name = 'B'), (SELECT id FROM categories WHERE name = '모자'), 2000),
    ((SELECT id FROM brands WHERE name = 'B'), (SELECT id FROM categories WHERE name = '양말'), 2000),
    ((SELECT id FROM brands WHERE name = 'B'), (SELECT id FROM categories WHERE name = '액세서리'), 2200),

    -- 브랜드 C
    ((SELECT id FROM brands WHERE name = 'C'), (SELECT id FROM categories WHERE name = '상의'), 10000),
    ((SELECT id FROM brands WHERE name = 'C'), (SELECT id FROM categories WHERE name = '아우터'), 6200),
    ((SELECT id FROM brands WHERE name = 'C'), (SELECT id FROM categories WHERE name = '바지'), 3300),
    ((SELECT id FROM brands WHERE name = 'C'), (SELECT id FROM categories WHERE name = '스니커즈'), 9200),
    ((SELECT id FROM brands WHERE name = 'C'), (SELECT id FROM categories WHERE name = '가방'), 2200),
    ((SELECT id FROM brands WHERE name = 'C'), (SELECT id FROM categories WHERE name = '모자'), 1900),
    ((SELECT id FROM brands WHERE name = 'C'), (SELECT id FROM categories WHERE name = '양말'), 2200),
    ((SELECT id FROM brands WHERE name = 'C'), (SELECT id FROM categories WHERE name = '액세서리'), 2100),

    -- 브랜드 D
    ((SELECT id FROM brands WHERE name = 'D'), (SELECT id FROM categories WHERE name = '상의'), 10100),
    ((SELECT id FROM brands WHERE name = 'D'), (SELECT id FROM categories WHERE name = '아우터'), 5100),
    ((SELECT id FROM brands WHERE name = 'D'), (SELECT id FROM categories WHERE name = '바지'), 3000),
    ((SELECT id FROM brands WHERE name = 'D'), (SELECT id FROM categories WHERE name = '스니커즈'), 9500),
    ((SELECT id FROM brands WHERE name = 'D'), (SELECT id FROM categories WHERE name = '가방'), 2500),
    ((SELECT id FROM brands WHERE name = 'D'), (SELECT id FROM categories WHERE name = '모자'), 1500),
    ((SELECT id FROM brands WHERE name = 'D'), (SELECT id FROM categories WHERE name = '양말'), 2400),
    ((SELECT id FROM brands WHERE name = 'D'), (SELECT id FROM categories WHERE name = '액세서리'), 2000),
    
    -- 브랜드 E
    ((SELECT id FROM brands WHERE name = 'E'), (SELECT id FROM categories WHERE name = '상의'), 10700),
    ((SELECT id FROM brands WHERE name = 'E'), (SELECT id FROM categories WHERE name = '아우터'), 5000),
    ((SELECT id FROM brands WHERE name = 'E'), (SELECT id FROM categories WHERE name = '바지'), 3800),
    ((SELECT id FROM brands WHERE name = 'E'), (SELECT id FROM categories WHERE name = '스니커즈'), 9900),
    ((SELECT id FROM brands WHERE name = 'E'), (SELECT id FROM categories WHERE name = '가방'), 2300),
    ((SELECT id FROM brands WHERE name = 'E'), (SELECT id FROM categories WHERE name = '모자'), 1800),
    ((SELECT id FROM brands WHERE name = 'E'), (SELECT id FROM categories WHERE name = '양말'), 2100),
    ((SELECT id FROM brands WHERE name = 'E'), (SELECT id FROM categories WHERE name = '액세서리'), 2100),

    -- 브랜드 F
    ((SELECT id FROM brands WHERE name = 'F'), (SELECT id FROM categories WHERE name = '상의'), 11200),
    ((SELECT id FROM brands WHERE name = 'F'), (SELECT id FROM categories WHERE name = '아우터'), 7200),
    ((SELECT id FROM brands WHERE name = 'F'), (SELECT id FROM categories WHERE name = '바지'), 4000),
    ((SELECT id FROM brands WHERE name = 'F'), (SELECT id FROM categories WHERE name = '스니커즈'), 9300),
    ((SELECT id FROM brands WHERE name = 'F'), (SELECT id FROM categories WHERE name = '가방'), 2100),
    ((SELECT id FROM brands WHERE name = 'F'), (SELECT id FROM categories WHERE name = '모자'), 1600),
    ((SELECT id FROM brands WHERE name = 'F'), (SELECT id FROM categories WHERE name = '양말'), 2300),
    ((SELECT id FROM brands WHERE name = 'F'), (SELECT id FROM categories WHERE name = '액세서리'), 1900),

    -- 브랜드 G
    ((SELECT id FROM brands WHERE name = 'G'), (SELECT id FROM categories WHERE name = '상의'), 10500),
    ((SELECT id FROM brands WHERE name = 'G'), (SELECT id FROM categories WHERE name = '아우터'), 5800),
    ((SELECT id FROM brands WHERE name = 'G'), (SELECT id FROM categories WHERE name = '바지'), 3900),
    ((SELECT id FROM brands WHERE name = 'G'), (SELECT id FROM categories WHERE name = '스니커즈'), 9000),
    ((SELECT id FROM brands WHERE name = 'G'), (SELECT id FROM categories WHERE name = '가방'), 2200),
    ((SELECT id FROM brands WHERE name = 'G'), (SELECT id FROM categories WHERE name = '모자'), 1700),
    ((SELECT id FROM brands WHERE name = 'G'), (SELECT id FROM categories WHERE name = '양말'), 2100),
    ((SELECT id FROM brands WHERE name = 'G'), (SELECT id FROM categories WHERE name = '액세서리'), 2000),

    -- 브랜드 H
    ((SELECT id FROM brands WHERE name = 'H'), (SELECT id FROM categories WHERE name = '상의'), 10800),
    ((SELECT id FROM brands WHERE name = 'H'), (SELECT id FROM categories WHERE name = '아우터'), 6300),
    ((SELECT id FROM brands WHERE name = 'H'), (SELECT id FROM categories WHERE name = '바지'), 3100),
    ((SELECT id FROM brands WHERE name = 'H'), (SELECT id FROM categories WHERE name = '스니커즈'), 9700),
    ((SELECT id FROM brands WHERE name = 'H'), (SELECT id FROM categories WHERE name = '가방'), 2100),
    ((SELECT id FROM brands WHERE name = 'H'), (SELECT id FROM categories WHERE name = '모자'), 1600),
    ((SELECT id FROM brands WHERE name = 'H'), (SELECT id FROM categories WHERE name = '양말'), 2000),
    ((SELECT id FROM brands WHERE name = 'H'), (SELECT id FROM categories WHERE name = '액세서리'), 2000),
    
    -- 브랜드 I
    ((SELECT id FROM brands WHERE name = 'I'), (SELECT id FROM categories WHERE name = '상의'), 11400),
    ((SELECT id FROM brands WHERE name = 'I'), (SELECT id FROM categories WHERE name = '아우터'), 6700),
    ((SELECT id FROM brands WHERE name = 'I'), (SELECT id FROM categories WHERE name = '바지'), 3200),
    ((SELECT id FROM brands WHERE name = 'I'), (SELECT id FROM categories WHERE name = '스니커즈'), 9500),
    ((SELECT id FROM brands WHERE name = 'I'), (SELECT id FROM categories WHERE name = '가방'), 2400),
    ((SELECT id FROM brands WHERE name = 'I'), (SELECT id FROM categories WHERE name = '모자'), 1700),
    ((SELECT id FROM brands WHERE name = 'I'), (SELECT id FROM categories WHERE name = '양말'), 1700),
    ((SELECT id FROM brands WHERE name = 'I'), (SELECT id FROM categories WHERE name = '액세서리'), 2400);