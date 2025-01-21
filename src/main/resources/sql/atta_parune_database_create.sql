-- --------------------------------------------------------
-- 호스트:                          localhost
-- 서버 버전:                        11.4.3-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- atta_parune 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `atta_parune` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `atta_parune`;

-- 테이블 atta_parune.admin 구조 내보내기
CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(20) NOT NULL COMMENT '스프링 시큐리티에 사용될 권한  목록',
  `aid` varchar(50) NOT NULL COMMENT 'uique 적용 해야함',
  `apw` varchar(100) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL COMMENT '아이디, 비밀번호 찾기 시 활용할 용도',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `phone` varchar(11) NOT NULL COMMENT '- 를 뺀 11자리 번호',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `aid` (`aid`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.admin:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.company 구조 내보내기
CREATE TABLE IF NOT EXISTS `company` (
  `company_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_cd` varchar(4) NOT NULL COMMENT '회사코드',
  `name` varchar(50) NOT NULL,
  `adress` varchar(300) NOT NULL,
  `ceo_name` varchar(30) NOT NULL,
  `business_number` varchar(10) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `current_point` int(11) NOT NULL DEFAULT 0,
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`company_id`),
  UNIQUE KEY `company_cd` (`company_cd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.company:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.company_admin 구조 내보내기
CREATE TABLE IF NOT EXISTS `company_admin` (
  `company_id` bigint(20) NOT NULL,
  `admin_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  KEY `FK_company_TO_company_admin_1` (`company_id`),
  KEY `FK_admin_TO_company_admin_1` (`admin_id`),
  CONSTRAINT `FK_admin_TO_company_admin_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `FK_company_TO_company_admin_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.company_admin:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.company_point_deposits 구조 내보내기
CREATE TABLE IF NOT EXISTS `company_point_deposits` (
  `deposits_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NOT NULL COMMENT '시스템 관리자가 포인트를 입금한다.',
  `point_amount` int(11) NOT NULL COMMENT '입금된 포인트 값',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`deposits_id`),
  KEY `FK_company_admin_TO_company_point_deposits_1` (`admin_id`),
  CONSTRAINT `FK_company_admin_TO_company_point_deposits_1` FOREIGN KEY (`admin_id`) REFERENCES `company_admin` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.company_point_deposits:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.company_ticket_creation_time 구조 내보내기
CREATE TABLE IF NOT EXISTS `company_ticket_creation_time` (
  `creation_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `creation_time_start` time NOT NULL,
  `creation_time_end` time NOT NULL,
  `creation_time_name` varchar(30) NOT NULL COMMENT '점심시간, 석식시간 등의 명칭',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`creation_id`),
  KEY `FK_company_TO_company_ticket_creation_time_1` (`company_id`),
  CONSTRAINT `FK_company_TO_company_ticket_creation_time_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.company_ticket_creation_time:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.holiday 구조 내보내기
CREATE TABLE IF NOT EXISTS `holiday` (
  `holiday_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `restaurant_id` bigint(20) NOT NULL,
  `closed_days` date NOT NULL COMMENT '휴무일 목록 (배열 형식)',
  PRIMARY KEY (`holiday_id`),
  KEY `FK_restaurant_TO_holiday_1` (`restaurant_id`),
  CONSTRAINT `FK_restaurant_TO_holiday_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.holiday:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.meal_time 구조 내보내기
CREATE TABLE IF NOT EXISTS `meal_time` (
  `meal_time_pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `reservation_id` bigint(20) NOT NULL,
  `restaurant_id` bigint(20) NOT NULL COMMENT '예약 pk로도 식당 pk를 알아낼 수 있지만 특정 식당의 평균 식사시간을 구하는 쿼리문이 복잡해질 것 같아 식당 pk도 넣음',
  `start_meal_date` datetime DEFAULT NULL COMMENT '예약 생성시 예약 시간으로 초기화 함',
  `end_meal_date` datetime DEFAULT NULL COMMENT '결재 시 시간으로 업데이트 함',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`meal_time_pk`),
  KEY `FK_reservation_TO_meal_time_1` (`reservation_id`),
  KEY `FK_restaurant_TO_meal_time_1` (`restaurant_id`),
  CONSTRAINT `FK_reservation_TO_meal_time_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`),
  CONSTRAINT `FK_restaurant_TO_meal_time_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.meal_time:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.order 구조 내보내기
CREATE TABLE IF NOT EXISTS `order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `restaurant_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `reservation_yn` smallint(6) NOT NULL DEFAULT 0 COMMENT '0: 미예약, 1: 예약',
  `reservation_status` smallint(6) NOT NULL DEFAULT 0 COMMENT '0:미승인, 1:승인, 2:거부, 3:취소',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`order_id`),
  KEY `FK_restaurant_TO_order_1` (`restaurant_id`),
  KEY `FK_user_TO_order_1` (`user_id`),
  CONSTRAINT `FK_restaurant_TO_order_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`),
  CONSTRAINT `FK_user_TO_order_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.order:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.order_detail 구조 내보내기
CREATE TABLE IF NOT EXISTS `order_detail` (
  `order_detail_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `menu_count` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`order_detail_id`),
  KEY `FK_order_TO_order_detail_1` (`order_id`),
  KEY `FK_restaurant_menu_TO_order_detail_1` (`menu_id`),
  CONSTRAINT `FK_order_TO_order_detail_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  CONSTRAINT `FK_restaurant_menu_TO_order_detail_1` FOREIGN KEY (`menu_id`) REFERENCES `restaurant_menu` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.order_detail:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.reservation 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservation` (
  `reservation_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `reservation_time` datetime NOT NULL,
  `reservation_people_count` smallint(6) NOT NULL DEFAULT 1,
  `reservation_cancel_reason` varchar(100) DEFAULT NULL COMMENT '취소사유에 대한 간단한 설명',
  `user_phone` varchar(11) DEFAULT NULL COMMENT '- 를 뺀 11자리 숫자',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`reservation_id`),
  KEY `FK_order_TO_reservation_1` (`order_id`),
  CONSTRAINT `FK_order_TO_reservation_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.reservation:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.restaurant 구조 내보내기
CREATE TABLE IF NOT EXISTS `restaurant` (
  `restaurant_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `restaurant_name` varchar(50) NOT NULL,
  `restaurant_address` varchar(200) NOT NULL,
  `restaurant_number` varchar(20) NOT NULL,
  `business_number` varchar(20) NOT NULL,
  `operating_hours` varchar(50) NOT NULL,
  `restaurant_description` varchar(200) DEFAULT NULL,
  `status` smallint(6) DEFAULT 0 NOT NULL COMMENT '0(영업중), 1(브레이크타임), 9(영업종료)',
  `max_capacity` int(11) NOT NULL COMMENT '좌석 테이블 삭제',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`restaurant_id`),
  KEY `FK_admin_TO_restaurant_1` (`admin_id`),
  KEY `FK_restaurant_category_TO_restaurant_1` (`category_id`),
  CONSTRAINT `FK_admin_TO_restaurant_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `FK_restaurant_category_TO_restaurant_1` FOREIGN KEY (`category_id`) REFERENCES `restaurant_category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.restaurant:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.restaurant_category 구조 내보내기
CREATE TABLE IF NOT EXISTS `restaurant_category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL COMMENT '한식, 중식, 일식 등 시스템 관리자가 등록함',
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.restaurant_category:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.restaurant_menu 구조 내보내기
CREATE TABLE IF NOT EXISTS `restaurant_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) NOT NULL,
  `menu_name` varchar(20) NOT NULL,
  `menu_pic` varchar(100) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `details` varchar(100) DEFAULT NULL,
  `available` tinyint(1) NOT NULL COMMENT '기본값: true',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`menu_id`),
  KEY `FK_restaurant_menu_category_TO_restaurant_menu_1` (`category_id`),
  CONSTRAINT `FK_restaurant_menu_category_TO_restaurant_menu_1` FOREIGN KEY (`category_id`) REFERENCES `restaurant_menu_category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.restaurant_menu:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.restaurant_menu_category 구조 내보내기
CREATE TABLE IF NOT EXISTS `restaurant_menu_category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `restaurant_id` bigint(20) NOT NULL,
  `category_name` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `FK_restaurant_TO_restaurant_menu_category_1` (`restaurant_id`),
  CONSTRAINT `FK_restaurant_TO_restaurant_menu_category_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.restaurant_menu_category:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.restaurant_pic 구조 내보내기
CREATE TABLE IF NOT EXISTS `restaurant_pic` (
  `pic_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `restaurant_id` bigint(20) NOT NULL,
  `file_path` varchar(300) NOT NULL COMMENT '사진 파일 저장 경로',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`pic_id`),
  KEY `FK_restaurant_TO_restaurant_pic_1` (`restaurant_id`),
  CONSTRAINT `FK_restaurant_TO_restaurant_pic_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.restaurant_pic:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.role 구조 내보내기
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` varchar(20) NOT NULL COMMENT '스프링 시큐리티에 사용될 권한  목록',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.role:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.ticket 구조 내보내기
CREATE TABLE IF NOT EXISTS `ticket` (
  `ticket_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `expired_date` datetime NOT NULL COMMENT '식권의 만료시간 예약이 존재하면 예약시간에서 2시간 뒤 아니면 생성일에서 2시간 뒤',
  `ticket_status` smallint(6) NOT NULL DEFAULT 0 COMMENT '0:미사용, 1:사용',
  `use_date` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`ticket_id`),
  KEY `FK_order_TO_ticket_1` (`order_id`),
  CONSTRAINT `FK_order_TO_ticket_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.ticket:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL,
  `role_id` varchar(20) NOT NULL COMMENT '일반 사용자는 무조건 ROLE_USER 만 가진다. 스프링 시큐리티에서 구분되야 해서 넣음.',
  `uid` varchar(8) NOT NULL COMMENT '회사코드 4자리 + 사원코드 4자리 (사원코드는 시스템에서 관리하는게 아니라 따로 관리되는 회사 시스템이 있다고 가정. 회사코드는 중복되면 안되기에 회사 테이블에 컬럼으로 넣음.)',
  `upw` varchar(100) NOT NULL,
  `name` varchar(30) NOT NULL COMMENT '회사 식권 테이블링 시스템이기 때문에 별칭(닉네임)은 필요없다고 판단했음.',
  `pic` varchar(50) DEFAULT NULL,
  `email` varchar(100) NOT NULL COMMENT '아이디 찾기, 비밀번호 찾기 시 활용할 용도',
  `phone` varchar(11) NOT NULL COMMENT '- 빼고 11자리 휴대폰 번호',
  `point` int(11) NOT NULL DEFAULT 0 COMMENT '쿼리로 계산해서 가져오는게 비용이 많이 든다고 생각해 입금과 결재 시 트렌젝션 처리하고 더하기, 빼기함',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `email` (`email`),
  KEY `FK_company_TO_user_1` (`company_id`),
  CONSTRAINT `FK_company_TO_user_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.user:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.user_payment_member 구조 내보내기
CREATE TABLE IF NOT EXISTS `user_payment_member` (
  `order_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `select_date` datetime DEFAULT NULL COMMENT '승인, 거부를 선택한 일자',
  `point` int(11) DEFAULT NULL COMMENT '승인해야할 포인트 금액',
  `approval_status` smallint(6) NOT NULL DEFAULT 0 COMMENT '0: 미승인, 1:승인, 2:거부',
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  KEY `FK_order_TO_user_payment_member_1` (`order_id`),
  KEY `FK_user_TO_user_payment_member_1` (`user_id`),
  CONSTRAINT `FK_order_TO_user_payment_member_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  CONSTRAINT `FK_user_TO_user_payment_member_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.user_payment_member:~0 rows (대략적) 내보내기

-- 테이블 atta_parune.user_point_deposits 구조 내보내기
CREATE TABLE IF NOT EXISTS `user_point_deposits` (
  `deposits_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `admin_id` bigint(20) NOT NULL COMMENT '입금한 관리자의 아이디',
  `point_amount` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`deposits_id`),
  KEY `FK_user_TO_user_point_deposits_1` (`user_id`),
  KEY `FK_company_admin_TO_user_point_deposits_1` (`admin_id`),
  CONSTRAINT `FK_company_admin_TO_user_point_deposits_1` FOREIGN KEY (`admin_id`) REFERENCES `company_admin` (`admin_id`),
  CONSTRAINT `FK_user_TO_user_point_deposits_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 atta_parune.user_point_deposits:~0 rows (대략적) 내보내기

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
