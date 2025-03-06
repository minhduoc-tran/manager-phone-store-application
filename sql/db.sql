-- Tạo bảng tb_user (Nhân viên, Quản lý, Admin)
CREATE TABLE tb_user (
    user_id BIGINT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    gender SMALLINT,
    phone_of_birth DATE,
    created_at DATE NOT NULL DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE,
    image_url VARCHAR(255)
);

-- Tạo bảng tb_role (Vai trò)
CREATE TABLE tb_role (
    role_id BIGINT PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    created_at DATE NOT NULL DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE
);

-- Tạo bảng tb_permission (Quyền)
CREATE TABLE tb_permission (
    permission_id BIGINT PRIMARY KEY,
    method VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    created_at DATE NOT NULL DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE
);

-- Tạo bảng tb_branch (Chi nhánh)
CREATE TABLE tb_branch (
    branch_id BIGINT PRIMARY KEY,
    branch_name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    created_at DATE NOT NULL DEFAULT CURRENT_DATE
);

-- Tạo bảng tb_category (Danh mục)
CREATE TABLE tb_category (
    category_id BIGINT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    created_at DATE NOT NULL DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE
);

-- Tạo bảng tb_brand (Thương hiệu)
CREATE TABLE tb_brand (
    brand_id BIGINT PRIMARY KEY,
    brand_name VARCHAR(100) NOT NULL,
    created_at DATE NOT NULL DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE
);

-- Tạo bảng tb_product (Sản phẩm)
CREATE TABLE tb_product (
    product_id BIGINT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    category_id BIGINT NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    discount_price DOUBLE PRECISION,
    description VARCHAR(255),
    brand_id BIGINT NOT NULL,
    created_at DATE NOT NULL DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE
);

-- Tạo bảng tb_product_image (Hình ảnh sản phẩm)
CREATE TABLE tb_product_image (
    image_id BIGINT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    thumbnail VARCHAR(255),
    created_at DATE NOT NULL DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE
);

-- Tạo bảng tb_inventory (Kho)
CREATE TABLE tb_inventory (
    inventory_id BIGINT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    branch_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    last_updated DATE NOT NULL DEFAULT CURRENT_DATE
);

-- Tạo bảng tb_customer (Khách hàng)
CREATE TABLE tb_customer (
    customer_id BIGINT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20) NOT NULL,
    image_url VARCHAR(255),
    created_at DATE NOT NULL DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE
);

-- Tạo bảng tb_order (Đơn hàng)
CREATE TABLE tb_order (
    order_id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    total_amount DOUBLE PRECISION NOT NULL,
    status VARCHAR(50) NOT NULL,
    order_type VARCHAR(50), -- Thêm để phân biệt online/in-store
    created_at DATE NOT NULL DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE
);

-- Tạo bảng tb_order_detail (Chi tiết đơn hàng)
CREATE TABLE tb_order_detail (
    order_detail_id BIGINT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity BIGINT NOT NULL,
    unit_price DOUBLE PRECISION NOT NULL,
    sub_price DOUBLE PRECISION NOT NULL,
    created_at DATE NOT NULL DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE
);

-- Tạo bảng trung gian user_has_branch
CREATE TABLE user_has_branch (
    user_id BIGINT NOT NULL,
    branch_id BIGINT NOT NULL
);

-- Tạo bảng trung gian user_has_role
CREATE TABLE user_has_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);

-- Tạo bảng trung gian role_has_permission
CREATE TABLE role_has_permission (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL
);

-- Thêm các khóa ngoại (Foreign Keys)

-- Khóa ngoại cho tb_user
ALTER TABLE user_has_branch
    ADD FOREIGN KEY (user_id) REFERENCES tb_user(user_id),
    ADD FOREIGN KEY (branch_id) REFERENCES tb_branch(branch_id);

ALTER TABLE user_has_role
    ADD FOREIGN KEY (user_id) REFERENCES tb_user(user_id),
    ADD FOREIGN KEY (role_id) REFERENCES tb_role(role_id);

-- Khóa ngoại cho tb_role
ALTER TABLE role_has_permission
    ADD FOREIGN KEY (role_id) REFERENCES tb_role(role_id),
    ADD FOREIGN KEY (permission_id) REFERENCES tb_permission(permission_id);

-- Khóa ngoại cho tb_product
ALTER TABLE tb_product
    ADD FOREIGN KEY (category_id) REFERENCES tb_category(category_id),
    ADD FOREIGN KEY (brand_id) REFERENCES tb_brand(brand_id);

-- Khóa ngoại cho tb_product_image
ALTER TABLE tb_product_image
    ADD FOREIGN KEY (product_id) REFERENCES tb_product(product_id);

-- Khóa ngoại cho tb_inventory
ALTER TABLE tb_inventory
    ADD FOREIGN KEY (product_id) REFERENCES tb_product(product_id),
    ADD FOREIGN KEY (branch_id) REFERENCES tb_branch(branch_id);

-- Khóa ngoại cho tb_order
ALTER TABLE tb_order
    ADD FOREIGN KEY (customer_id) REFERENCES tb_customer(customer_id);

-- Khóa ngoại cho tb_order_detail
ALTER TABLE tb_order_detail
    ADD FOREIGN KEY (order_id) REFERENCES tb_order(order_id),
    ADD FOREIGN KEY (product_id) REFERENCES tb_product(product_id);