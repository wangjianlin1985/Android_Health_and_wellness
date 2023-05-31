/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : foodapp_db

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2018-04-19 14:14:23
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `admins`
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginid` varchar(255) DEFAULT NULL,
  `passwords` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for `collects`
-- ----------------------------
DROP TABLE IF EXISTS `collects`;
CREATE TABLE `collects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `shopid` int(11) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collects
-- ----------------------------
INSERT INTO `collects` VALUES ('6', '1', '6', '2018-04-18');

-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL COMMENT '回复人ID',
  `username` text COMMENT '回复人姓名',
  `createtime` varchar(20) DEFAULT NULL COMMENT '回复时间',
  `body` text COMMENT '回复内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for `shops`
-- ----------------------------
DROP TABLE IF EXISTS `shops`;
CREATE TABLE `shops` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL COMMENT '纬度',
  `lon` varchar(255) DEFAULT NULL COMMENT '经度',
  `specialty` text COMMENT '特色菜',
  `img_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shops
-- ----------------------------
INSERT INTO `shops` VALUES ('1', '3', '安可休闲养生', '23.119774', '113.335573', '休闲养生', '01.jpg');
INSERT INTO `shops` VALUES ('2', '3', '贵联升', '23.119732', '113.335573', '休闲养生', '02.jpg');
INSERT INTO `shops` VALUES ('3', '3', '南阳堂', '23.119776', '113.335577', '休闲养生', '03.jpg');
INSERT INTO `shops` VALUES ('4', '3', '品容升', '23.149774', '113.335573', '休闲养生', '01.jpg');
INSERT INTO `shops` VALUES ('5', '3', '玉波楼', '23.119755', '113.335593', '休闲养生', '02.jpg');
INSERT INTO `shops` VALUES ('6', '3', '中艾堂养生馆', '23.119733', '113.335573', '艾灸疗法简称灸法，是运用艾绒或其他药物在体表的穴位上烧灼、温熨，借灸火的热力以及药物的作用，通过经络的传导，以起到温通气血、扶正祛邪，达到防治疾病的一种治法', 'aitang.jpg');

-- ----------------------------
-- Table structure for `t_zhenz`
-- ----------------------------
DROP TABLE IF EXISTS `t_zhenz`;
CREATE TABLE `t_zhenz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `zhengzhuang` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_zhenz
-- ----------------------------
INSERT INTO `t_zhenz` VALUES ('1', '高血压');
INSERT INTO `t_zhenz` VALUES ('2', '高血糖');
INSERT INTO `t_zhenz` VALUES ('3', '心慌');
INSERT INTO `t_zhenz` VALUES ('4', '头晕');
INSERT INTO `t_zhenz` VALUES ('5', '失眠');

-- ----------------------------
-- Table structure for `types`
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` text CHARACTER SET utf8,
  `intro` text,
  `fid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES ('1', '养生方法', '粤菜，即广东地方风味菜，有着悠久的历史，以特有的菜式和韵味，独树一帜，中国汉族八大菜系之一，发源于岭南，在国内外享有盛誉。粤菜的主要性格为：取之自然，烹之自由，食之自在！粤菜广义上来说由广州菜（亦称广府菜）、潮州菜（亦称“潮汕菜”）、东江菜（属客家菜）组成，以广州菜作为代表。', '0');
INSERT INTO `types` VALUES ('2', '健康症状', '川菜又名蜀菜调味多变，菜式多样，口味清鲜醇浓并重，以善用麻辣著称，并以其别具一格的烹调方法和浓郁的地方风味。川菜作为中国汉族八大菜系之一，在中国烹饪史上占有重要地位，它取材广泛，调味多变，菜式多样，口味清鲜醇浓并重，以善用麻辣著称，并以其别具一格的烹调方法和浓郁的地方风味，融会了东南西北各方的特点，博采众家之长，善于吸收，善于创新，享誉中外。 四川菜历史悠久，在国内外都享有很高的声誉。四川位于长江上游，气候温和，雨量充沛，群山环抱，江河纵横，盛产粮油，蔬菜瓜果四季不断，家畜家禽品种齐全，山岳深丘特产熊、鹿、獐、狍、银耳、虫草、竹笋等山珍野味，江河湖泊又有江团、雅鱼、岩鲤、中华鲟。', '0');
INSERT INTO `types` VALUES ('3', '养生指导', '东北菜是指东北，包括黑龙江、吉林、辽宁、内蒙古东部的烹饪菜系，因东北地区独特而统一的人文环境和自然环境，东北各地的饮食高度相似，但细分之下也有吉菜、辽菜等菜系的说法。在“八大”菜系里面，东北菜是没有排上号的，但这并没有妨碍它的生意。即使在远离其发源地的广州，东北菜也能像木棉花一样，开得热烈、豪迈。东北菜的形成过程也融合了一些中国其他菜系和汉族饮食的特点。东北菜的特点是一菜多味、咸甜分明、用料广泛、火候足、滋味浓郁、色鲜味浓、酥烂香脆，烹调方法长于熘、爆、扒、炸、烧、蒸、炖、，以炖、酱、烤为主要特点。代表菜有白肉血肠、锅包肉、东北乱炖、溜肉段、地三鲜、猪肉炖粉条、小鸡炖榛蘑、扒熊掌、拔丝地瓜、酱骨架，杀猪菜等等，东北人喜好吃的酸菜和用蔬菜蘸大酱的蘸酱菜也是东北饮食区别于其他菜系的一大特点。', '0');
INSERT INTO `types` VALUES ('4', '食疗方法', '食疗方法', '1');
INSERT INTO `types` VALUES ('5', '中医养生', '中医养生', '1');
INSERT INTO `types` VALUES ('6', '生活小妙招', '生活小妙招', '1');
INSERT INTO `types` VALUES ('7', '亚健康类型', '亚健康类型', '2');
INSERT INTO `types` VALUES ('8', '亚健康症状', '亚健康症状', '2');
INSERT INTO `types` VALUES ('9', '对症养生', '对症养生', '3');
INSERT INTO `types` VALUES ('10', '养生资讯', '养生资讯', '3');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginid` varchar(255) DEFAULT NULL,
  `name` text,
  `passwords` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'test00', '王勇', '111111');
INSERT INTO `users` VALUES ('2', 'zhangsan', '张三', '111111');
INSERT INTO `users` VALUES ('3', 'iuuuuhh', '肯定的', '111111');
INSERT INTO `users` VALUES ('4', 'xiaomi', '小明', '111111');

-- ----------------------------
-- Table structure for `xinxi`
-- ----------------------------
DROP TABLE IF EXISTS `xinxi`;
CREATE TABLE `xinxi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` text COMMENT '标题',
  `typeid` int(11) DEFAULT NULL COMMENT '类型ID',
  `typename` text COMMENT '类型名称',
  `intro` text COMMENT '简介',
  `img_url` varchar(255) DEFAULT NULL COMMENT '封面图片名称',
  `practice` text COMMENT '单价',
  `fid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xinxi
-- ----------------------------
INSERT INTO `xinxi` VALUES ('1', '黑色食物能补肾', '4', '食疗方法', '黑色食物滋补，对人体有益，但也不可多食。黑木耳是极好的防癌食品，由于其纤维素含量极高，能很好地清除血管内的垃圾和致癌物质，预防心脑血管疾病，并且稀释大肠中的致癌物质，有助于预防大肠癌。又名乌豆，味甘性平，入脾经、肾经。传统中医学认为，黑豆有助于抗衰老，具有医食同疗的特殊功能。紫菜,含丰富的钙、铁元素，不仅有益治疗妇女儿童贫血，而且可以促进儿童和老人的骨骼、牙齿生长和保健。', '1111.jpg', '', '1');
INSERT INTO `xinxi` VALUES ('2', '高血压的按摩穴位', '5', '中医养生', '头转向一侧，鼓起来的这一条筋叫桥弓，用手从上往下推（可以抹一点润滑油或膏药），对急性高血压（血压突然升高引起头疼、眼睛胀疼）效果特别好，一般2~3分钟血压就降下来了。', 'xuewei.jpg', '', '1');
INSERT INTO `xinxi` VALUES ('3', '非常不健康的生活习惯', '6', '生活小妙招', '用面巾纸包食物,为了增白，许多厂家在生产纸过程中往往会使用荧光增白剂。荧光增白剂是一种「化学毒物」，当与食品（尤其是油脂类食物）接触后，温度越高，会加速其向食品中迁移。口服摄入的荧光增白剂，在体内不易被分解，长期摄入毒性会在肝脏中积累，产生潜在的致癌因素。', 'zhijin.jpg', '', '1');
INSERT INTO `xinxi` VALUES ('4', '亚健康类型之心型亚健康', '7', '亚健康类型', '心型表现是时常心慌、气短、失眠多梦。心型亚健康会导致的疾病有高血压、冠心病、中风、心梗等。 心型亚健康怎么调理:我们可以从饮食，运动，和经络三方面进行调理。 \n心型亚健康之饮食调理：静心茶：配方：玫瑰花3克，西洋参3克，麦冬5克，五味子2克，莲心1克，百合5克，红枣2个。用热水泡制饮用，七日为一个疗程。 \n心型亚健康之运动调理：口诀：静心气，小肠助，咬舌尖，火冒处。 时间在中午12点到1点之间做6次以上效果最好。 \n心型之经络调理：晚上9点到11点泡完脚后，身体微出汗之后，可按摩劳宫穴，内外关穴各36次。', 'xinhuangqiduan.jpg', '', '2');
INSERT INTO `xinxi` VALUES ('5', '亚健康症状之阳虚', '8', '亚健康症状', '阳虚者,表现为:手脚冰凉、夜尿频繁、大便不成形。可选用下列补药:杜仲、鹿茸。杜仲可以补肝肾、强筋骨、安胎。饮食调控:每周吃 500克狗肉, 250克大枣。运动调控:采自家之火温煦肾阳,每日早晚各一次。方 药调控:制附子、上肉桂、熟地黄、山茱萸、山药、茯苓、泽泻、丹皮、生黄芪、生姜、阳起石,每 两 天一 剂水煎服,每四周为一个疗程。坚持做到以上三点,两个月后基本所有的症状都可以消除。 扶阳药方:金匮肾气丸', 'bingliang.jpg', '', '2');
INSERT INTO `xinxi` VALUES ('6', '气虚质养生', '9', '对症养生', '体型要么过于消瘦、要么偏胖，心悸气短，容易疲劳、出汗，舌苔淡红。宜：吃性质平和、偏温、容易消化的食物，如小米、银耳、豆腐、鸡肉等；注意保暖双脚，预防呼吸道疾病；选择运动量小且教暖和的运动，比如散步、太极拳等。忌：吃油腻、不易消化、生冷、苦寒的食物。', 'qixuzhi.png', null, '3');
INSERT INTO `xinxi` VALUES ('7', '饭后不能立即吃水果', '10', '养生资讯', '吃水果的正确时间是饭前1个小时和饭后2个小时左右(柿子等不宜在饭前吃的水果除外)。饭前吃水果，有很多好处。首先，水果中许多成分均是水溶性的，饭前吃有利于身体必需营养素的吸收。其次，水果是低热量食物，其平均热量仅为同等重量面食的1/4，同等猪肉等肉食的约1/10。先吃低热量食物，比较容易把握一顿饭里总的热量摄入。第三，许多水果本身容易被氧化、腐败，先吃水果可缩短它在胃中的停留时间，降低其氧化、腐败程度，减少可能对身体造成的不利影响。 ', 'shuiguo.png', null, '3');
INSERT INTO `xinxi` VALUES ('10', '薏苡米食疗', '4', '食疗方法', '珠玉二宝粥：薏苡仁、山药各60g，捣为粗末，加水煮至烂熟，再将柿霜饼25g，切碎，调入溶化，随意服食。源于《衷中参西录》。山药、薏苡仁均为清补脾肺之药;柿霜饼为柿霜熬成，可润肺益脾。用于脾肺阴虚，饮食懒进，虚热劳嗽。', '812ffd63-6bc0-40a2-ac7f-c067e7b6e7eb.png\n', 'undefined', '1');
