--
-- Database: `spring_eventms`
--
CREATE DATABASE IF NOT EXISTS `spring_eventms` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `spring_eventms`;

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `title` varchar(500) NOT NULL,
  `details` text NOT NULL,
  `category` int(11) NOT NULL,
  `subcategory` int(11) NOT NULL,
  `fromdate` date DEFAULT NULL,
  `todate` date DEFAULT NULL,
  `price` float NOT NULL,
  `incharge` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `creationtime` timestamp NOT NULL DEFAULT current_timestamp(),
  `updationtime` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id`, `user`, `title`, `details`, `category`, `subcategory`, `fromdate`, `todate`, `price`, `incharge`, `status`, `creationtime`, `updationtime`) VALUES
(1, 17, 'Lorem ipsum dolor sit amet', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec tellus quam, elementum a ante ut leo.', 2, 1, '2025-03-01', '2025-03-03', 16500, 9, 1, '2025-02-15 14:21:46', '2025-02-15 15:10:38');

-- --------------------------------------------------------

--
-- Table structure for table `booking_events`
--

CREATE TABLE `booking_events` (
  `id` int(11) NOT NULL,
  `booking` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `venue` varchar(500) NOT NULL,
  `location` int(11) NOT NULL,
  `guests` int(11) NOT NULL,
  `start` timestamp NULL DEFAULT NULL,
  `end` timestamp NULL DEFAULT NULL,
  `status` int(11) NOT NULL,
  `creationtime` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `booking_events`
--

INSERT INTO `booking_events` (`id`, `booking`, `title`, `venue`, `location`, `guests`, `start`, `end`, `status`, `creationtime`) VALUES
(2, 1, 'test1', 'ttttttttt', 577, 0, '2025-03-01 04:30:00', '2025-03-01 07:30:00', 0, '2025-02-15 19:28:31'),
(3, 1, 'tes2', 'ttttttttt', 577, 10, '2025-03-02 04:30:00', '2025-03-02 05:30:00', 1, '2025-02-15 19:29:09');

-- --------------------------------------------------------

--
-- Table structure for table `booking_payments`
--

CREATE TABLE `booking_payments` (
  `id` int(11) NOT NULL,
  `booking` int(11) NOT NULL,
  `amount` float NOT NULL,
  `paymentmode` varchar(500) NOT NULL,
  `transactionid` varchar(500) NOT NULL,
  `creationtime` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking_payments`
--

INSERT INTO `booking_payments` (`id`, `booking`, `amount`, `paymentmode`, `transactionid`, `creationtime`) VALUES
(1, 1, 10000, 'UPI', '213213213', '2025-02-15 17:36:12'),
(2, 1, 5000, 'CARD', '0f75e9f1-7d8e-4036-ad89-43fa851f7a1b', '2025-02-15 17:53:31');

-- --------------------------------------------------------

--
-- Table structure for table `calendar`
--

CREATE TABLE `calendar` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `startdate` varchar(48) NOT NULL,
  `enddate` varchar(48) NOT NULL,
  `allDay` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `calendar`
--

INSERT INTO `calendar` (`id`, `title`, `startdate`, `enddate`, `allDay`) VALUES
(2, 'New Event', '2018-02-25T00:00:00+2:00', '2018-02-25T00:00:00+2:00', 'false'),
(3, 'New Event', '2018-03-06T00:00:00+2:00', '2018-03-06T00:00:00+2:00', 'false'),
(4, 'New Event', '2018-03-02T00:00:00+2:00', '2018-03-02T00:00:00+2:00', 'false'),
(5, 'New Event', '2018-02-27T00:00:00+2:00', '2018-02-27T00:00:00+2:00', 'false'),
(6, 'New Event', '2018-03-06T00:00:00+2:00', '2018-03-06T00:00:00+2:00', 'false');

-- --------------------------------------------------------

--
-- Table structure for table `cardcheck`
--

CREATE TABLE `cardcheck` (
  `id` int(11) NOT NULL,
  `cardtype` varchar(255) NOT NULL,
  `cardno` varchar(255) NOT NULL,
  `expiry` varchar(255) NOT NULL,
  `cvvcode` varchar(255) NOT NULL,
  `valid` int(11) NOT NULL,
  `result` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cardcheck`
--

INSERT INTO `cardcheck` (`id`, `cardtype`, `cardno`, `expiry`, `cvvcode`, `valid`, `result`) VALUES
(1, 'Mastercard', '5425230000000000', '12/04', '481', 0, 'Invalid exp. date'),
(2, 'Mastercard', '2222420000000000', '08/25', '681', 1, 'Success'),
(3, 'Mastercard', '2223000000000000', '09/25', '229', 1, 'Success'),
(4, 'Visa', '4917480000000000', '01/25', '538', 0, 'Failure'),
(5, 'JCB', '3566000000000000', '12/25', '520', 1, 'Success'),
(6, 'Visa', '4263980000000000', '12/25', '667', 1, 'Success'),
(8, 'JCB', '3530110000000000', '03/25', '983', 0, 'Failure'),
(9, 'Cabal1', '6271700000000000', '07/25', '183', 1, 'Success'),
(12, 'Amex', '374245000000000', '09/25', '246', 1, 'Success'),
(13, 'Amex', '378282000000000', '05/25', '814', 0, 'Failure'),
(14, 'China Union Pay', '6250940000000000', '06/25', '803', 1, 'Success'),
(15, 'Cencosud1', '6034930000000000', '06/25', '965', 1, 'Success'),
(16, 'ELO1', '6362970000000000', '08/25', '213', 1, 'Success'),
(17, 'Visa1', '4001920000000000', '09/25', '818', 1, 'Success'),
(18, 'Hipercard1', '6062830000000000', '09/25', '574', 1, 'Success'),
(19, 'Tarjeta Shopping1', '6034880000000000', '09/25', '328', 1, 'Success'),
(20, 'Visa1', '4007700000000000', '10/25', '790', 1, 'Success'),
(21, 'Naranja1', '5895630000000000', '11/25', '869', 1, 'Success'),
(22, 'Nativa1', '5200530000000000', '11/25', '459', 1, 'Success'),
(23, 'Discover', '60115600000000000', '12/25', '642', 1, 'Success'),
(24, 'Discover', '6011000000000000', '12/25', '223', 1, 'Success'),
(25, 'Argencard1', '5011050000000000', '12/25', '773', 1, 'Success');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Birthday'),
(2, 'Marriage'),
(7, 'Wedding anniversary');

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities` (
  `id` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`id`, `state`, `name`) VALUES
(1, 1, 'Kupwara'),
(2, 1, 'Badgam'),
(3, 1, 'Leh(Ladakh)'),
(4, 1, 'Kargil'),
(5, 1, 'Punch'),
(6, 1, 'Rajouri'),
(7, 1, 'Kathua'),
(8, 1, 'Baramula'),
(9, 1, 'Bandipore'),
(10, 1, 'Srinagar'),
(11, 1, 'Ganderbal'),
(12, 1, 'Pulwama'),
(13, 1, 'Shupiyan'),
(14, 1, 'Anantnag'),
(15, 1, 'Kulgam'),
(16, 1, 'Doda'),
(17, 1, 'Ramban'),
(18, 1, 'Kishtwar'),
(19, 1, 'Udhampur'),
(20, 1, 'Reasi'),
(21, 1, 'Jammu'),
(22, 1, 'Samba'),
(23, 2, 'Chamba'),
(24, 2, 'Kangra'),
(25, 2, 'Lahul & Spiti'),
(26, 2, 'Kullu'),
(27, 2, 'Mandi'),
(28, 2, 'Hamirpur'),
(29, 2, 'Una'),
(30, 2, 'Bilaspur'),
(31, 2, 'Solan'),
(32, 2, 'Sirmaur'),
(33, 2, 'Shimla'),
(34, 2, 'Kinnaur'),
(35, 3, 'Gurdaspur'),
(36, 3, 'Kapurthala '),
(37, 3, 'Jalandhar'),
(38, 3, 'Hoshiarpur'),
(39, 3, 'Shahid Bhagat Singh Nagar '),
(40, 3, 'Fatehgarh Sahib'),
(41, 3, 'Ludhiana'),
(42, 3, 'Moga'),
(43, 3, 'Firozpur'),
(44, 3, 'Muktsar'),
(45, 3, 'Faridkot'),
(46, 3, 'Bathinda'),
(47, 3, 'Mansa'),
(48, 3, 'Patiala'),
(49, 3, 'Amritsar '),
(50, 3, 'Tarn Taran'),
(51, 3, 'Rupnagar'),
(52, 3, 'Sahibzada Ajit Singh Nagar'),
(53, 3, 'Sangrur'),
(54, 3, 'Barnala'),
(55, 4, 'Chandigarh'),
(56, 5, 'Uttarkashi'),
(57, 5, 'Chamoli'),
(58, 5, 'Rudraprayag'),
(59, 5, 'Tehri Garhwal'),
(60, 5, 'Dehradun'),
(61, 5, 'Garhwal'),
(62, 5, 'Pithoragarh'),
(63, 5, 'Bageshwar'),
(64, 5, 'Almora'),
(65, 5, 'Champawat'),
(66, 5, 'Nainital'),
(67, 5, 'Udham Singh Nagar'),
(68, 5, 'Hardwar'),
(69, 6, 'Panchkula'),
(70, 6, 'Ambala'),
(71, 6, 'Yamunanagar'),
(72, 6, 'Kurukshetra'),
(73, 6, 'Kaithal'),
(74, 6, 'Karnal'),
(75, 6, 'Panipat'),
(76, 6, 'Sonipat'),
(77, 6, 'Jind'),
(78, 6, 'Fatehabad'),
(79, 6, 'Sirsa'),
(80, 6, 'Hisar'),
(81, 6, 'Bhiwani'),
(82, 6, 'Rohtak'),
(83, 6, 'Jhajjar'),
(84, 6, 'Mahendragarh'),
(85, 6, 'Rewari'),
(86, 6, 'Gurgaon'),
(87, 6, 'Mewat '),
(88, 6, 'Faridabad'),
(89, 6, 'Palwal '),
(90, 7, 'North West'),
(91, 7, 'North'),
(92, 7, 'North East'),
(93, 7, 'East'),
(94, 7, 'New Delhi'),
(95, 7, 'Central'),
(96, 7, 'West'),
(97, 7, 'South West'),
(98, 7, 'South'),
(99, 8, 'Ganganagar '),
(100, 8, 'Hanumangarh'),
(101, 8, 'Bikaner'),
(102, 8, 'Churu'),
(103, 8, 'Jhunjhunun'),
(104, 8, 'Alwar'),
(105, 8, 'Bharatpur'),
(106, 8, 'Dhaulpur'),
(107, 8, 'Karauli'),
(108, 8, 'Sawai Madhopur'),
(109, 8, 'Dausa'),
(110, 8, 'Jaipur'),
(111, 8, 'Sikar'),
(112, 8, 'Nagaur'),
(113, 8, 'Jodhpur'),
(114, 8, 'Jaisalmer'),
(115, 8, 'Barmer'),
(116, 8, 'Jalor'),
(117, 8, 'Sirohi'),
(118, 8, 'Pali'),
(119, 8, 'Ajmer'),
(120, 8, 'Tonk'),
(121, 8, 'Bundi'),
(122, 8, 'Bhilwara'),
(123, 8, 'Rajsamand'),
(124, 8, 'Dungarpur'),
(125, 8, 'Banswara'),
(126, 8, 'Chittaurgarh'),
(127, 8, 'Kota'),
(128, 8, 'Baran'),
(129, 8, 'Jhalawar'),
(130, 8, 'Udaipur'),
(131, 8, 'Pratapgarh'),
(132, 9, 'Saharanpur'),
(133, 9, 'Muzaffarnagar'),
(134, 9, 'Bijnor'),
(135, 9, 'Moradabad'),
(136, 9, 'Rampur'),
(137, 9, 'Jyotiba Phule Nagar'),
(138, 9, 'Meerut'),
(139, 9, 'Baghpat'),
(140, 9, 'Ghaziabad'),
(141, 9, 'Gautam Buddha Nagar'),
(142, 9, 'Bulandshahr '),
(143, 9, 'Aligarh'),
(144, 9, 'Mahamaya Nagar'),
(145, 9, 'Mathura'),
(146, 9, 'Agra'),
(147, 9, 'Firozabad'),
(148, 9, 'Mainpuri'),
(149, 9, 'Budaun'),
(150, 9, 'Bareilly'),
(151, 9, 'Pilibhit'),
(152, 9, 'Shahjahanpur'),
(153, 9, 'Kheri'),
(154, 9, 'Sitapur'),
(155, 9, 'Hardoi'),
(156, 9, 'Unnao'),
(157, 9, 'Lucknow'),
(158, 9, 'Rae Bareli'),
(159, 9, 'Farrukhabad'),
(160, 9, 'Kannauj'),
(161, 9, 'Etawah'),
(162, 9, 'Auraiya'),
(163, 9, 'Kanpur Dehat'),
(164, 9, 'Kanpur Nagar'),
(165, 9, 'Jalaun '),
(166, 9, 'Jhansi'),
(167, 9, 'Lalitpur'),
(168, 9, 'Hamirpur'),
(169, 9, 'Mahoba'),
(170, 9, 'Banda'),
(171, 9, 'Chitrakoot'),
(172, 9, 'Fatehpur'),
(173, 9, 'Pratapgarh'),
(174, 9, 'Kaushambi'),
(175, 9, 'Allahabad '),
(176, 9, 'Bara Banki'),
(177, 9, 'Faizabad'),
(178, 9, 'Ambedkar Nagar'),
(179, 9, 'Sultanpur'),
(180, 9, 'Bahraich'),
(181, 9, 'Shrawasti'),
(182, 9, 'Balrampur'),
(183, 9, 'Gonda'),
(184, 9, 'Siddharthnagar'),
(185, 9, 'Basti'),
(186, 9, 'Sant Kabir Nagar'),
(187, 9, 'Mahrajganj'),
(188, 9, 'Gorakhpur'),
(189, 9, 'Kushinagar'),
(190, 9, 'Deoria'),
(191, 9, 'Azamgarh'),
(192, 9, 'Mau'),
(193, 9, 'Ballia'),
(194, 9, 'Jaunpur'),
(195, 9, 'Ghazipur'),
(196, 9, 'Chandauli'),
(197, 9, 'Varanasi'),
(198, 9, 'Sant Ravidas Nagar (Bhadohi)'),
(199, 9, 'Mirzapur'),
(200, 9, 'Sonbhadra'),
(201, 9, 'Etah'),
(202, 9, 'Kanshiram Nagar'),
(203, 10, 'Pashchim Champaran'),
(204, 10, 'Purba Champaran'),
(205, 10, 'Sheohar'),
(206, 10, 'Sitamarhi'),
(207, 10, 'Madhubani'),
(208, 10, 'Supaul'),
(209, 10, 'Araria'),
(210, 10, 'Kishanganj'),
(211, 10, 'Purnia'),
(212, 10, 'Katihar'),
(213, 10, 'Madhepura'),
(214, 10, 'Saharsa'),
(215, 10, 'Darbhanga'),
(216, 10, 'Muzaffarpur'),
(217, 10, 'Gopalganj'),
(218, 10, 'Siwan'),
(219, 10, 'Saran'),
(220, 10, 'Vaishali'),
(221, 10, 'Samastipur'),
(222, 10, 'Begusarai'),
(223, 10, 'Khagaria'),
(224, 10, 'Bhagalpur'),
(225, 10, 'Banka'),
(226, 10, 'Munger'),
(227, 10, 'Lakhisarai'),
(228, 10, 'Sheikhpura'),
(229, 10, 'Nalanda'),
(230, 10, 'Patna'),
(231, 10, 'Bhojpur'),
(232, 10, 'Buxar'),
(233, 10, 'Kaimur (Bhabua)'),
(234, 10, 'Rohtas'),
(235, 10, 'Aurangabad'),
(236, 10, 'Gaya'),
(237, 10, 'Nawada'),
(238, 10, 'Jamui'),
(239, 10, 'Jehanabad '),
(240, 10, 'Arwal'),
(241, 11, 'North  District'),
(242, 11, 'West District'),
(243, 11, 'South District'),
(244, 11, 'East District'),
(245, 12, 'Tawang'),
(246, 12, 'West Kameng'),
(247, 12, 'East Kameng'),
(248, 12, 'Papum Pare'),
(249, 12, 'Upper Subansiri'),
(250, 12, 'West Siang'),
(251, 12, 'East Siang'),
(252, 12, 'Upper Siang'),
(253, 12, 'Changlang'),
(254, 12, 'Tirap'),
(255, 12, 'Lower Subansiri'),
(256, 12, 'Kurung Kumey'),
(257, 12, 'Dibang Valley'),
(258, 12, 'Lower Dibang Valley'),
(259, 12, 'Lohit'),
(260, 12, 'Anjaw'),
(261, 13, 'Mon'),
(262, 13, 'Mokokchung'),
(263, 13, 'Zunheboto'),
(264, 13, 'Wokha'),
(265, 13, 'Dimapur '),
(266, 13, 'Phek'),
(267, 13, 'Tuensang'),
(268, 13, 'Longleng'),
(269, 13, 'Kiphire'),
(270, 13, 'Kohima'),
(271, 13, 'Peren'),
(272, 14, 'Senapati'),
(273, 14, 'Tamenglong '),
(274, 14, 'Churachandpur'),
(275, 14, 'Bishnupur'),
(276, 14, 'Thoubal'),
(277, 14, 'Imphal West'),
(278, 14, 'Imphal East'),
(279, 14, 'Ukhrul'),
(280, 14, 'Chandel'),
(281, 15, 'Mamit'),
(282, 15, 'Kolasib'),
(283, 15, 'Aizawl'),
(284, 15, 'Champhai'),
(285, 15, 'Serchhip'),
(286, 15, 'Lunglei'),
(287, 15, 'Lawngtlai'),
(288, 15, 'Saiha'),
(289, 16, 'West Tripura '),
(290, 16, 'South Tripura '),
(291, 16, 'Dhalai'),
(292, 16, 'North Tripura'),
(293, 17, 'West Garo Hills'),
(294, 17, 'East Garo Hills'),
(295, 17, 'South Garo Hills'),
(296, 17, 'West Khasi Hills'),
(297, 17, 'Ribhoi'),
(298, 17, 'East Khasi Hills'),
(299, 17, 'Jaintia Hills'),
(300, 18, 'Kokrajhar'),
(301, 18, 'Dhubri'),
(302, 18, 'Goalpara'),
(303, 18, 'Barpeta'),
(304, 18, 'Morigaon'),
(305, 18, 'Nagaon'),
(306, 18, 'Sonitpur'),
(307, 18, 'Lakhimpur'),
(308, 18, 'Dhemaji'),
(309, 18, 'Tinsukia'),
(310, 18, 'Dibrugarh'),
(311, 18, 'Sivasagar'),
(312, 18, 'Jorhat'),
(313, 18, 'Golaghat'),
(314, 18, 'Karbi Anglong'),
(315, 18, 'Dima Hasao'),
(316, 18, 'Cachar'),
(317, 18, 'Karimganj'),
(318, 18, 'Hailakandi'),
(319, 18, 'Bongaigaon'),
(320, 18, 'Chirang'),
(321, 18, 'Kamrup'),
(322, 18, 'Kamrup Metropolitan'),
(323, 18, 'Nalbari'),
(324, 18, 'Baksa'),
(325, 18, 'Darrang'),
(326, 18, 'Udalguri'),
(327, 19, 'Darjiling '),
(328, 19, 'Jalpaiguri '),
(329, 19, 'Koch Bihar '),
(330, 19, 'Uttar Dinajpur'),
(331, 19, 'Dakshin Dinajpur'),
(332, 19, 'Maldah '),
(333, 19, 'Murshidabad '),
(334, 19, 'Birbhum'),
(335, 19, 'Barddhaman '),
(336, 19, 'Nadia '),
(337, 19, 'North Twenty Four Parganas'),
(338, 19, 'Hugli '),
(339, 19, 'Bankura '),
(340, 19, 'Puruliya'),
(341, 19, 'Haora '),
(342, 19, 'Kolkata'),
(343, 19, 'South Twenty Four Parganas'),
(344, 19, 'Paschim Medinipur'),
(345, 19, 'Purba Medinipur'),
(346, 20, 'Garhwa '),
(347, 20, 'Chatra'),
(348, 20, 'Kodarma'),
(349, 20, 'Giridih'),
(350, 20, 'Deoghar'),
(351, 20, 'Godda'),
(352, 20, 'Sahibganj'),
(353, 20, 'Pakur'),
(354, 20, 'Dhanbad'),
(355, 20, 'Bokaro'),
(356, 20, 'Lohardaga'),
(357, 20, 'Purbi Singhbhum'),
(358, 20, 'Palamu'),
(359, 20, 'Latehar'),
(360, 20, 'Hazaribagh'),
(361, 20, 'Ramgarh'),
(362, 20, 'Dumka'),
(363, 20, 'Jamtara'),
(364, 20, 'Ranchi'),
(365, 20, 'Khunti'),
(366, 20, 'Gumla'),
(367, 20, 'Simdega'),
(368, 20, 'Pashchimi Singhbhum'),
(369, 20, 'Saraikela-Kharsawan'),
(370, 21, 'Bargarh'),
(371, 21, 'Jharsuguda'),
(372, 21, 'Sambalpur'),
(373, 21, 'Debagarh'),
(374, 21, 'Sundargarh'),
(375, 21, 'Kendujhar'),
(376, 21, 'Mayurbhanj'),
(377, 21, 'Baleshwar'),
(378, 21, 'Bhadrak'),
(379, 21, 'Kendrapara '),
(380, 21, 'Jagatsinghapur '),
(381, 21, 'Cuttack'),
(382, 21, 'Jajapur  '),
(383, 21, 'Dhenkanal'),
(384, 21, 'Anugul  '),
(385, 21, 'Nayagarh  '),
(386, 21, 'Khordha '),
(387, 21, 'Puri'),
(388, 21, 'Ganjam'),
(389, 21, 'Gajapati'),
(390, 21, 'Kandhamal'),
(391, 21, 'Baudh'),
(392, 21, 'Subarnapur'),
(393, 21, 'Balangir'),
(394, 21, 'Nuapada'),
(395, 21, 'Kalahandi'),
(396, 21, 'Rayagada  '),
(397, 21, 'Nabarangapur '),
(398, 21, 'Koraput'),
(399, 21, 'Malkangiri  '),
(400, 22, 'Koriya'),
(401, 22, 'Surguja'),
(402, 22, 'Jashpur '),
(403, 22, 'Raigarh'),
(404, 22, 'Korba '),
(405, 22, 'Janjgir - Champa'),
(406, 22, 'Bilaspur'),
(407, 22, 'Kabeerdham'),
(408, 22, 'Rajnandgaon'),
(409, 22, 'Durg'),
(410, 22, 'Raipur'),
(411, 22, 'Mahasamund'),
(412, 22, 'Dhamtari '),
(413, 22, 'Uttar Bastar Kanker'),
(414, 22, 'Bastar'),
(415, 22, 'Narayanpur'),
(416, 22, 'Dakshin Bastar Dantewada'),
(417, 22, 'Bijapur'),
(418, 23, 'Sheopur '),
(419, 23, 'Morena'),
(420, 23, 'Bhind'),
(421, 23, 'Gwalior'),
(422, 23, 'Datia'),
(423, 23, 'Shivpuri'),
(424, 23, 'Tikamgarh'),
(425, 23, 'Chhatarpur'),
(426, 23, 'Panna'),
(427, 23, 'Sagar'),
(428, 23, 'Damoh'),
(429, 23, 'Satna'),
(430, 23, 'Rewa'),
(431, 23, 'Umaria'),
(432, 23, 'Neemuch '),
(433, 23, 'Mandsaur'),
(434, 23, 'Ratlam'),
(435, 23, 'Ujjain'),
(436, 23, 'Shajapur'),
(437, 23, 'Dewas'),
(438, 23, 'Dhar'),
(439, 23, 'Indore'),
(440, 23, 'Khargone (West Nimar)'),
(441, 23, 'Barwani '),
(442, 23, 'Rajgarh'),
(443, 23, 'Vidisha'),
(444, 23, 'Bhopal'),
(445, 23, 'Sehore'),
(446, 23, 'Raisen'),
(447, 23, 'Betul'),
(448, 23, 'Harda '),
(449, 23, 'Hoshangabad'),
(450, 23, 'Katni '),
(451, 23, 'Jabalpur'),
(452, 23, 'Narsimhapur'),
(453, 23, 'Dindori '),
(454, 23, 'Mandla'),
(455, 23, 'Chhindwara'),
(456, 23, 'Seoni'),
(457, 23, 'Balaghat'),
(458, 23, 'Guna'),
(459, 23, 'Ashoknagar'),
(460, 23, 'Shahdol'),
(461, 23, 'Anuppur'),
(462, 23, 'Sidhi'),
(463, 23, 'Singrauli'),
(464, 23, 'Jhabua'),
(465, 23, 'Alirajpur'),
(466, 23, 'Khandwa (East Nimar)'),
(467, 23, 'Burhanpur'),
(468, 24, 'Kachchh'),
(469, 24, 'Banas Kantha'),
(470, 24, 'Patan  '),
(471, 24, 'Mahesana'),
(472, 24, 'Sabar Kantha'),
(473, 24, 'Gandhinagar'),
(474, 24, 'Ahmadabad'),
(475, 24, 'Surendranagar'),
(476, 24, 'Rajkot'),
(477, 24, 'Jamnagar'),
(478, 24, 'Porbandar '),
(479, 24, 'Junagadh'),
(480, 24, 'Amreli'),
(481, 24, 'Bhavnagar'),
(482, 24, 'Anand  '),
(483, 24, 'Kheda'),
(484, 24, 'Panch Mahals'),
(485, 24, 'Dohad  '),
(486, 24, 'Vadodara'),
(487, 24, 'Narmada'),
(488, 24, 'Bharuch'),
(489, 24, 'The Dangs'),
(490, 24, 'Navsari  '),
(491, 24, 'Valsad'),
(492, 24, 'Surat'),
(493, 24, 'Tapi'),
(494, 25, 'Diu'),
(495, 25, 'Daman'),
(496, 26, 'Dadra & Nagar Haveli'),
(497, 27, 'Nandurbar'),
(498, 27, 'Dhule'),
(499, 27, 'Jalgaon'),
(500, 27, 'Buldana'),
(501, 27, 'Akola'),
(502, 27, 'Washim'),
(503, 27, 'Amravati'),
(504, 27, 'Wardha'),
(505, 27, 'Nagpur'),
(506, 27, 'Bhandara'),
(507, 27, 'Gondiya'),
(508, 27, 'Gadchiroli'),
(509, 27, 'Chandrapur'),
(510, 27, 'Yavatmal'),
(511, 27, 'Nanded'),
(512, 27, 'Hingoli'),
(513, 27, 'Parbhani'),
(514, 27, 'Jalna'),
(515, 27, 'Aurangabad'),
(516, 27, 'Nashik'),
(517, 27, 'Thane'),
(518, 27, 'Mumbai Suburban'),
(519, 27, 'Mumbai'),
(520, 27, 'Raigarh'),
(521, 27, 'Pune'),
(522, 27, 'Ahmadnagar'),
(523, 27, 'Bid'),
(524, 27, 'Latur'),
(525, 27, 'Osmanabad'),
(526, 27, 'Solapur'),
(527, 27, 'Satara'),
(528, 27, 'Ratnagiri'),
(529, 27, 'Sindhudurg'),
(530, 27, 'Kolhapur'),
(531, 27, 'Sangli'),
(532, 36, 'Adilabad'),
(533, 36, 'Nizamabad'),
(534, 36, 'Karimnagar'),
(535, 36, 'Medak'),
(536, 36, 'Hyderabad'),
(537, 36, 'Rangareddy'),
(538, 36, 'Mahbubnagar'),
(539, 36, 'Nalgonda'),
(540, 36, 'Warangal'),
(541, 36, 'Khammam'),
(542, 28, 'Srikakulam'),
(543, 28, 'Vizianagaram'),
(544, 28, 'Visakhapatnam'),
(545, 28, 'East Godavari'),
(546, 28, 'West Godavari'),
(547, 28, 'Krishna'),
(548, 28, 'Guntur'),
(549, 28, 'Prakasam'),
(550, 28, 'Sri Potti Sriramulu Nellore'),
(551, 28, 'Y.S.R.'),
(552, 28, 'Kurnool'),
(553, 28, 'Anantapur'),
(554, 28, 'Chittoor'),
(555, 29, 'Belgaum'),
(556, 29, 'Bagalkot '),
(557, 29, 'Bijapur'),
(558, 29, 'Bidar'),
(559, 29, 'Raichur'),
(560, 29, 'Koppal'),
(561, 29, 'Gadag'),
(562, 29, 'Dharwad'),
(563, 29, 'Uttara Kannada'),
(564, 29, 'Haveri'),
(565, 29, 'Bellary'),
(566, 29, 'Chitradurga'),
(567, 29, 'Davanagere'),
(568, 29, 'Shimoga'),
(569, 29, 'Udupi'),
(570, 29, 'Chikmagalur'),
(571, 29, 'Tumkur'),
(572, 29, 'Bangalore'),
(573, 29, 'Mandya'),
(574, 29, 'Hassan'),
(575, 29, 'Dakshina Kannada'),
(576, 29, 'Kodagu'),
(577, 29, 'Mysore'),
(578, 29, 'Chamarajanagar'),
(579, 29, 'Gulbarga'),
(580, 29, 'Yadgir'),
(581, 29, 'Kolar'),
(582, 29, 'Chikkaballapura'),
(583, 29, 'Bangalore Rural'),
(584, 29, 'Ramanagara'),
(585, 30, 'North Goa'),
(586, 30, 'South Goa'),
(587, 31, 'Lakshadweep'),
(588, 32, 'Kasaragod'),
(589, 32, 'Kannur'),
(590, 32, 'Wayanad'),
(591, 32, 'Kozhikode'),
(592, 32, 'Malappuram'),
(593, 32, 'Palakkad'),
(594, 32, 'Thrissur'),
(595, 32, 'Ernakulam'),
(596, 32, 'Idukki '),
(597, 32, 'Kottayam'),
(598, 32, 'Alappuzha'),
(599, 32, 'Pathanamthitta'),
(600, 32, 'Kollam'),
(601, 32, 'Thiruvananthapuram'),
(602, 33, 'Thiruvallur'),
(603, 33, 'Chennai'),
(604, 33, 'Kancheepuram'),
(605, 33, 'Vellore'),
(606, 33, 'Tiruvannamalai'),
(607, 33, 'Viluppuram'),
(608, 33, 'Salem'),
(609, 33, 'Namakkal   '),
(610, 33, 'Erode'),
(611, 33, 'The Nilgiris'),
(612, 33, 'Dindigul'),
(613, 33, 'Karur '),
(614, 33, 'Tiruchirappalli'),
(615, 33, 'Perambalur  '),
(616, 33, 'Ariyalur  '),
(617, 33, 'Cuddalore'),
(618, 33, 'Nagapattinam  '),
(619, 33, 'Thiruvarur'),
(620, 33, 'Thanjavur'),
(621, 33, 'Pudukkottai'),
(622, 33, 'Sivaganga'),
(623, 33, 'Madurai'),
(624, 33, 'Theni  '),
(625, 33, 'Virudhunagar'),
(626, 33, 'Ramanathapuram'),
(627, 33, 'Thoothukkudi'),
(628, 33, 'Tirunelveli '),
(629, 33, 'Kanniyakumari'),
(630, 33, 'Dharmapuri'),
(631, 33, 'Krishnagiri'),
(632, 33, 'Coimbatore'),
(633, 33, 'Tiruppur'),
(634, 34, 'Yanam'),
(635, 34, 'Puducherry'),
(636, 34, 'Mahe'),
(637, 34, 'Karaikal'),
(638, 35, 'Nicobars'),
(639, 35, 'North  & Middle Andaman'),
(640, 35, 'South Andaman');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `address` text NOT NULL,
  `city` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `pincode` varchar(20) NOT NULL,
  `creationtime` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `contactus`
--

CREATE TABLE `contactus` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `message` text NOT NULL,
  `creationtime` timestamp NOT NULL DEFAULT current_timestamp(),
  `updationtime` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `contactus`
--

INSERT INTO `contactus` (`id`, `name`, `email`, `mobile`, `message`, `creationtime`, `updationtime`) VALUES
(1, 'test', 'test@gmail.com', '1111111111', 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?', '2025-02-14 08:06:48', '2025-02-17 16:09:15'),
(2, 'test', 'test@gmail.com', '1111111111', 'test', '2025-02-14 08:07:28', NULL),
(3, 'test1', 'test1@gmail.com', '1111111111', 'test', '2025-02-14 08:08:33', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `features`
--

CREATE TABLE `features` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `features`
--

INSERT INTO `features` (`id`, `name`, `description`) VALUES
(1, 'Hair and Make Up', 'none'),
(2, 'Photographer', 'unlimited shot\r\nSoftCopy(CD/DVD)'),
(3, 'Hair And Make Up', 'unlimited shot'),
(4, 'Appetizers and Meal Service', 'Choice Six Hot/Cold, 3-Entr&eacute;e Buffet or Duet Plate'),
(5, 'Hair And Make Up', 'Our own professional worker'),
(6, 'Wedding Cake', 'Custom Wedding Cake'),
(7, 'Appetizers', 'Vegetable &amp; Cheese Platters'),
(8, 'DJ Services', 'DJ Services'),
(9, 'Bar Service', 'Bar Service'),
(10, 'Champagne &amp; Cider Toast', 'Champagne &amp; Cider Toast'),
(11, 'Hair And Make Up', 'hair cut that will change you life'),
(12, 'Invitations &amp; Accessories', 'Invitations &amp; Accessories'),
(13, 'DJ &amp; MC Services', 'DJ &amp; MC Services'),
(14, 'Chairs &amp; Linens', 'Chairs &amp; Linens'),
(15, 'Photographer', 'unlimited shot'),
(16, 'Bar Service', 'Beer, Wine'),
(17, 'Reception Decor', 'Stage Decor'),
(18, 'Appetizers and Meal Services', 'Choice Six Hot/Cold, 3-Entr&eacute;e Buffet or Duet Plate'),
(19, 'Invitations &amp; Accessories', 'none'),
(20, 'DJ &amp; MC Services', 'none'),
(21, 'Decorations', 'Stage Decorations'),
(22, 'Centerpieces', 'Standard'),
(23, 'Centerpieces', 'Centerpieces'),
(24, 'Photobooth', 'Photobooth'),
(25, 'Grand Sparklers', 'Grand Sparklers'),
(26, 'Specialty Lighting', 'Specialty Lighting');

-- --------------------------------------------------------

--
-- Table structure for table `gallery`
--

CREATE TABLE `gallery` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `caption` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `alternatetext` varchar(100) NOT NULL,
  `relate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gallery`
--

INSERT INTO `gallery` (`id`, `title`, `caption`, `description`, `alternatetext`, `relate`) VALUES
(37, '', '', '', '', 0),
(51, '', '', '', '', 0),
(52, 'test1', 'test2', 'test4', 'test3', 0);

-- --------------------------------------------------------

--
-- Table structure for table `guest`
--

CREATE TABLE `guest` (
  `id` int(11) NOT NULL,
  `booking` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `priority` int(11) NOT NULL,
  `outoftown` int(11) NOT NULL,
  `tracksandgifts` text NOT NULL,
  `city` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `guest`
--

INSERT INTO `guest` (`id`, `booking`, `name`, `priority`, `outoftown`, `tracksandgifts`, `city`) VALUES
(1, 1, 'josh dragon', 1, 1, 'asdasdsad', 0),
(2, 1, 'jane gest', 1, 1, 'color thing', 0),
(3, 2, 'jane gest', 1, 1, 'color thing', 0),
(4, 1, 'joshua deasi', 1, 1, 'asdasdasdasdasd', 0),
(5, 30, 'Betty M. Barber', 1, 1, 'Demo Text Demo Text', 0),
(6, 34, 'Guest One', 2, 1, 'Demo Demo Demo', 0);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `location` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `edate` date NOT NULL,
  `category` int(11) NOT NULL,
  `subcategory` int(11) NOT NULL,
  `booking` int(11) NOT NULL,
  `creationtime` timestamp NOT NULL DEFAULT current_timestamp(),
  `datepublished` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `title`, `description`, `location`, `status`, `edate`, `category`, `subcategory`, `booking`, `creationtime`, `datepublished`) VALUES
(31, 'MR. &amp; MRS. Atwood', '92-acre Tuscan-inspired estate offering indoor and outdoor event spaces, as well as a Four Diamond inn and renowned restaurant.', 577, 1, '2023-11-12', 2, 5, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(32, 'MR. &amp; MRS. Levy', 'Charming hilltop chapel with a rustic stone interior and graceful arched windows that offer unobstructed views of the Blue Ridge Mountains. Receptions are held at the nearby clubhouse where you can continue the celebration in the ballroom, or beneath a tent on the sprawling lawn.', 577, 1, '2024-01-22', 2, 3, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(35, 'MR. MRS Redmond', 'Mountain beauty meets warm sophistication at this resort, which boasts a luxury lodge, chic hotel, two country clubs, and plenty of indoor and outdoor wedding venues from refined ballrooms to lush gardens to shabby-chic wood pavilions.', 577, 1, '2023-11-06', 2, 5, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(40, 'MR. &amp; MRS. Collins', 'Lush, tropical garden setting with an open-air chapel and stunning views of the Ko\'olau Mountains.', 577, 1, '2024-05-26', 2, 5, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(45, 'MR. MRS  Graham', 'Faithful replica of a classic medieval castle, complete with turrets, a drawbridge, a vast collection of Medieval and Renaissance artifacts, and a romantic garden overlooking the Atlantic.', 577, 1, '2024-07-09', 2, 4, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(50, 'MR. MRS. Yorke', 'Fairy-tale Tudor Revival mansion and lakeside chapel surrounded by 100 acres of formal gardens and 900 acres of natural woodlands.', 577, 1, '2024-07-19', 2, 5, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(52, 'MR. &amp; MRS. Pearson', 'Beautifully restored historic home with elegant indoor event spaces, a Parterre Garden, and a lovely courtyard strung with market lights that\'s perfect for intimate celebrations.', 577, 1, '2024-05-01', 2, 5, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(55, 'MR. &amp; MRS. Raftery', 'Relaxed and refined private complex on 24 acres along the Carmel River where you can wed, dine, and stay.', 577, 1, '2024-04-07', 2, 5, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(57, 'MR. &amp; MRS. Brower', 'Exchange vows in a natural woodland &ldquo;cathedral&rdquo; surrounded by towering pines, followed by cocktails served from a charming tipi and a delightful tented reception in the heart of the woods.', 577, 1, '2024-07-28', 2, 4, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(60, 'MR. &amp; MRS. Reid', 'Romantic 1929 Art Deco, blufftop mansion with indoor and outdoor event venues that offer fantastic ocean and shoreline views.', 577, 1, '2024-02-14', 2, 5, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(62, 'MR. &amp; MRS. Squires', 'Magnificent 102-acre coastal resort on a bluff overlooking the stunning Palos Verdes Peninsula, boasting landscaped grounds, verdant lawns, and warm, elegant ballrooms with terraces&mdash;all with jaw-dropping views.', 577, 1, '2024-03-25', 2, 3, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(65, 'MR. &amp; MRS. Jones', 'Relaxed, full-service resort presents an alluring choices of indoor and outdoor venues, from al fresco ceremonies with sweeping Pacific vistas to luxe reception ballrooms with classic columns and hand-blown glass chandeliers.', 577, 1, '2023-12-08', 2, 5, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(67, 'MR. &amp; MRS. Russell', 'This Rockies hideaway boasts incredible mountain vistas and streamside paths, with multiple venue options for al fresco events, from the Shaker, an open-air platform tented with sailcloth, to the Highbanker Beach, a creekside lounge area perfect for cocktails and bonfires.', 577, 1, '2024-05-01', 2, 2, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(69, 'MR. &amp; MRS. Smith', 'This rustic farmstead getaway offers picture-perfect event spaces, including a hilltop pergola and the Chimney Pond Meadow, surrounded by neat rows of Christmas trees, and a stylishly rustic reception barn highlighted by Tuscan lights and globe chandeliers.', 577, 1, '2023-11-26', 2, 5, 0, '2025-02-14 09:37:36', '2025-02-14 09:37:36'),
(70, 'test', 'test', 572, 1, '2025-02-01', 0, 0, 0, '2025-02-15 06:23:56', '2025-02-15 06:28:34');

-- --------------------------------------------------------

--
-- Table structure for table `states`
--

CREATE TABLE `states` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `states`
--

INSERT INTO `states` (`id`, `name`) VALUES
(1, 'JAMMU & KASHMIR'),
(2, 'HIMACHAL PRADESH'),
(3, 'PUNJAB'),
(4, 'CHANDIGARH'),
(5, 'UTTARAKHAND'),
(6, 'HARYANA'),
(7, 'NCT OF DELHI'),
(8, 'RAJASTHAN'),
(9, 'UTTAR PRADESH'),
(10, 'BIHAR'),
(11, 'SIKKIM'),
(12, 'ARUNACHAL PRADESH'),
(13, 'NAGALAND'),
(14, 'MANIPUR'),
(15, 'MIZORAM'),
(16, 'TRIPURA'),
(17, 'MEGHALAYA'),
(18, 'ASSAM'),
(19, 'WEST BENGAL'),
(20, 'JHARKHAND'),
(21, 'ODISHA'),
(22, 'CHHATTISGARH'),
(23, 'MADHYA PRADESH'),
(24, 'GUJARAT'),
(25, 'DAMAN & DIU'),
(26, 'DADRA & NAGAR HAVELI'),
(27, 'MAHARASHTRA'),
(28, 'ANDHRA PRADESH'),
(29, 'KARNATAKA'),
(30, 'GOA'),
(31, 'LAKSHADWEEP'),
(32, 'KERALA'),
(33, 'TAMIL NADU'),
(34, 'PUDUCHERRY'),
(35, 'ANDAMAN & NICOBAR ISLANDS'),
(36, 'TELANGANA');

-- --------------------------------------------------------

--
-- Table structure for table `subcategories`
--

CREATE TABLE `subcategories` (
  `id` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subcategories`
--

INSERT INTO `subcategories` (`id`, `category`, `name`, `price`, `status`) VALUES
(1, 2, 'Classic', '16500.00', 1),
(2, 2, 'Elegant', '20000.00', 1),
(3, 2, 'Premier', '24000.00', 1),
(4, 2, 'Gold', '39500.00', 1),
(5, 2, 'Elite', '52000.00', 1),
(6, 1, 'Classic', '16500.00', 1),
(7, 1, 'Gold', '39500.00', 1),
(8, 1, 'Elite', '52000.00', 1),
(9, 1, 'test', '10000.00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `subcategory_features`
--

CREATE TABLE `subcategory_features` (
  `id` int(11) NOT NULL,
  `subcategory` int(11) NOT NULL,
  `feature` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subcategory_features`
--

INSERT INTO `subcategory_features` (`id`, `subcategory`, `feature`) VALUES
(2, 2, 1),
(3, 2, 2),
(4, 5, 1),
(7, 5, 4),
(8, 1, 1),
(9, 5, 6),
(10, 1, 7),
(11, 1, 8),
(12, 5, 9),
(13, 5, 10),
(15, 4, 4),
(16, 4, 1),
(17, 5, 12),
(18, 5, 13),
(19, 4, 6),
(20, 5, 14),
(21, 4, 2),
(22, 4, 9),
(23, 4, 17),
(24, 3, 1),
(25, 3, 18),
(26, 3, 12),
(27, 3, 13),
(28, 2, 7),
(29, 2, 21),
(30, 3, 6),
(31, 4, 13),
(32, 4, 22),
(33, 5, 22),
(34, 5, 24),
(35, 5, 25),
(36, 5, 26),
(56, 9, 1),
(57, 9, 4),
(58, 9, 7),
(59, 9, 8);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `type` varchar(100) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `password` varchar(500) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `status` int(11) NOT NULL,
  `creationtime` timestamp NOT NULL DEFAULT current_timestamp(),
  `updationtime` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `type`, `firstname`, `lastname`, `password`, `email`, `mobile`, `status`, `creationtime`, `updationtime`) VALUES
(1, 'ADMIN', 'admin', 'admin', '$2a$10$b1bgKtyrbRjse.AWoMPhXuExwvbR6TK3Lh8h12OWfSrfWPoUIUBJO', 'admin@mail.com', '8553305227', 1, '2025-02-14 11:13:37', '2025-02-14 11:13:47'),
(9, 'SUBADMIN', 'vijay', 'kumar', '$2a$10$kYjktitE5moWAq7qmyh1i.JfSMqPRgDl/Gf14b9I5MKgDw4EiDclu', 'vijayis2010@gmail.com', '8553305224', 1, '2025-02-15 03:33:37', '2025-02-15 03:33:37'),
(17, 'CLIENT', 'test', 'test', '$2a$10$wV6/9BTcwTgmKagHSYKkMeJJHLQo7rJplFWkawu3tcmUBnylQ01cO', 'test@gmail.com', '1111111112', 1, '2025-02-15 08:28:31', '2025-02-15 08:28:32');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `booking_events`
--
ALTER TABLE `booking_events`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `booking_payments`
--
ALTER TABLE `booking_payments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `calendar`
--
ALTER TABLE `calendar`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contactus`
--
ALTER TABLE `contactus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `features`
--
ALTER TABLE `features`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gallery`
--
ALTER TABLE `gallery`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `guest`
--
ALTER TABLE `guest`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `states`
--
ALTER TABLE `states`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subcategories`
--
ALTER TABLE `subcategories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subcategory_features`
--
ALTER TABLE `subcategory_features`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `mobile` (`mobile`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `booking_events`
--
ALTER TABLE `booking_events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `booking_payments`
--
ALTER TABLE `booking_payments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `calendar`
--
ALTER TABLE `calendar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `cities`
--
ALTER TABLE `cities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=642;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contactus`
--
ALTER TABLE `contactus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `features`
--
ALTER TABLE `features`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `gallery`
--
ALTER TABLE `gallery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `guest`
--
ALTER TABLE `guest`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT for table `states`
--
ALTER TABLE `states`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `subcategories`
--
ALTER TABLE `subcategories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `subcategory_features`
--
ALTER TABLE `subcategory_features`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
