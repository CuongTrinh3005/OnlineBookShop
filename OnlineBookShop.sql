--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

-- Started on 2021-06-30 21:08:05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 32959)
-- Name: authors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authors (
    author_id integer NOT NULL,
    author_name character varying(100) NOT NULL,
    address character varying(250),
    phone_number character varying(15)
);


ALTER TABLE public.authors OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 32957)
-- Name: authors_authorId_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."authors_authorId_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."authors_authorId_seq" OWNER TO postgres;

--
-- TOC entry 3070 (class 0 OID 0)
-- Dependencies: 209
-- Name: authors_authorId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."authors_authorId_seq" OWNED BY public.authors.author_id;


--
-- TOC entry 202 (class 1259 OID 32834)
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books (
    book_id bigint NOT NULL,
    book_name character varying(300) NOT NULL,
    unit_price numeric NOT NULL,
    quantity bigint,
    discount numeric,
    category_id character varying(8) NOT NULL,
    photo bytea,
    description text,
    specification text,
    view_count bigint,
    special boolean,
    available boolean,
    date_in date,
    date_update date,
    author_id integer,
    publisher_id integer
);


ALTER TABLE public.books OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 32832)
-- Name: books_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.books_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.books_id_seq OWNER TO postgres;

--
-- TOC entry 3071 (class 0 OID 0)
-- Dependencies: 201
-- Name: books_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.books_id_seq OWNED BY public.books.book_id;


--
-- TOC entry 200 (class 1259 OID 32802)
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
    category_id character varying(8) NOT NULL,
    category_name character varying(50) NOT NULL,
    description text
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 32850)
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    customer_id character varying(50) NOT NULL,
    password character varying(30) NOT NULL,
    customer_name character varying(100) NOT NULL,
    gender boolean,
    address character varying(200) NOT NULL,
    phone_number character varying(15) NOT NULL,
    email character varying(80),
    photo bytea,
    admin boolean
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 32848)
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customers_id_seq OWNER TO postgres;

--
-- TOC entry 3072 (class 0 OID 0)
-- Dependencies: 203
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.customer_id;


--
-- TOC entry 207 (class 1259 OID 32912)
-- Name: order_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_details (
    order_id bigint NOT NULL,
    book_id bigint NOT NULL,
    quantity_order integer NOT NULL,
    discount numeric,
    unit_price numeric NOT NULL
);


ALTER TABLE public.order_details OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 32861)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    order_id bigint NOT NULL,
    order_date date NOT NULL,
    customer_id character varying(50) NOT NULL,
    order_address character varying(200) NOT NULL,
    description text
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 32859)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO postgres;

--
-- TOC entry 3073 (class 0 OID 0)
-- Dependencies: 205
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.order_id;


--
-- TOC entry 212 (class 1259 OID 32967)
-- Name: publishers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.publishers (
    publisher_id integer NOT NULL,
    publisher_name character varying(100) NOT NULL,
    address character varying(250),
    phone_number character varying(15)
);


ALTER TABLE public.publishers OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 32965)
-- Name: publishers_publisherId_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."publishers_publisherId_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."publishers_publisherId_seq" OWNER TO postgres;

--
-- TOC entry 3074 (class 0 OID 0)
-- Dependencies: 211
-- Name: publishers_publisherId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."publishers_publisherId_seq" OWNED BY public.publishers.publisher_id;


--
-- TOC entry 208 (class 1259 OID 32930)
-- Name: ratings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ratings (
    customer_id character varying(50) NOT NULL,
    book_id bigint NOT NULL,
    date_rating date,
    level_rating integer
);


ALTER TABLE public.ratings OWNER TO postgres;

--
-- TOC entry 2896 (class 2604 OID 32962)
-- Name: authors author_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors ALTER COLUMN author_id SET DEFAULT nextval('public."authors_authorId_seq"'::regclass);


--
-- TOC entry 2893 (class 2604 OID 32837)
-- Name: books book_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books ALTER COLUMN book_id SET DEFAULT nextval('public.books_id_seq'::regclass);


--
-- TOC entry 2894 (class 2604 OID 32870)
-- Name: customers customer_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers ALTER COLUMN customer_id SET DEFAULT nextval('public.customers_id_seq'::regclass);


--
-- TOC entry 2895 (class 2604 OID 32864)
-- Name: orders order_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN order_id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- TOC entry 2897 (class 2604 OID 32970)
-- Name: publishers publisher_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publishers ALTER COLUMN publisher_id SET DEFAULT nextval('public."publishers_publisherId_seq"'::regclass);


--
-- TOC entry 3062 (class 0 OID 32959)
-- Dependencies: 210
-- Data for Name: authors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.authors (author_id, author_name, address, phone_number) FROM stdin;
1	Nguyen Nhat Anh	TP HCM	\N
\.


--
-- TOC entry 3054 (class 0 OID 32834)
-- Dependencies: 202
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.books (book_id, book_name, unit_price, quantity, discount, category_id, photo, description, specification, view_count, special, available, date_in, date_update, author_id, publisher_id) FROM stdin;
3	science	120.5	3	\N	SB	\N	\N	\N	\N	\N	\N	\N	\N	1	1
\.


--
-- TOC entry 3052 (class 0 OID 32802)
-- Dependencies: 200
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categories (category_id, category_name, description) FROM stdin;
SB	Science	\N
\.


--
-- TOC entry 3056 (class 0 OID 32850)
-- Dependencies: 204
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customers (customer_id, password, customer_name, gender, address, phone_number, email, photo, admin) FROM stdin;
abc	123	cuong	t	Dong  Nai	1234	\N	\N	t
\.


--
-- TOC entry 3059 (class 0 OID 32912)
-- Dependencies: 207
-- Data for Name: order_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.order_details (order_id, book_id, quantity_order, discount, unit_price) FROM stdin;
\.


--
-- TOC entry 3058 (class 0 OID 32861)
-- Dependencies: 206
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (order_id, order_date, customer_id, order_address, description) FROM stdin;
1	2021-06-09	abc	Bien Hoa	\N
\.


--
-- TOC entry 3064 (class 0 OID 32967)
-- Dependencies: 212
-- Data for Name: publishers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.publishers (publisher_id, publisher_name, address, phone_number) FROM stdin;
1	Nha xuat ban Hoa Binh	TP HCM	\N
\.


--
-- TOC entry 3060 (class 0 OID 32930)
-- Dependencies: 208
-- Data for Name: ratings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ratings (customer_id, book_id, date_rating, level_rating) FROM stdin;
\.


--
-- TOC entry 3075 (class 0 OID 0)
-- Dependencies: 209
-- Name: authors_authorId_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."authors_authorId_seq"', 1, true);


--
-- TOC entry 3076 (class 0 OID 0)
-- Dependencies: 201
-- Name: books_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.books_id_seq', 3, true);


--
-- TOC entry 3077 (class 0 OID 0)
-- Dependencies: 203
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customers_id_seq', 1, false);


--
-- TOC entry 3078 (class 0 OID 0)
-- Dependencies: 205
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 1, true);


--
-- TOC entry 3079 (class 0 OID 0)
-- Dependencies: 211
-- Name: publishers_publisherId_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."publishers_publisherId_seq"', 1, true);


--
-- TOC entry 2911 (class 2606 OID 32964)
-- Name: authors authors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (author_id);


--
-- TOC entry 2901 (class 2606 OID 32842)
-- Name: books books_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (book_id);


--
-- TOC entry 2899 (class 2606 OID 32809)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (category_id);


--
-- TOC entry 2903 (class 2606 OID 32872)
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (customer_id);


--
-- TOC entry 2907 (class 2606 OID 32919)
-- Name: order_details orderDetails_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT "orderDetails_pkey" PRIMARY KEY (book_id, order_id);


--
-- TOC entry 2905 (class 2606 OID 32869)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);


--
-- TOC entry 2913 (class 2606 OID 32972)
-- Name: publishers publishers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publishers
    ADD CONSTRAINT publishers_pkey PRIMARY KEY (publisher_id);


--
-- TOC entry 2909 (class 2606 OID 32934)
-- Name: ratings ratings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT ratings_pkey PRIMARY KEY (customer_id, book_id);


--
-- TOC entry 2915 (class 2606 OID 32973)
-- Name: books books_authorId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT "books_authorId_fkey" FOREIGN KEY (author_id) REFERENCES public.authors(author_id) NOT VALID;


--
-- TOC entry 2916 (class 2606 OID 32978)
-- Name: books books_publisherId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT "books_publisherId_fkey" FOREIGN KEY (publisher_id) REFERENCES public.publishers(publisher_id) NOT VALID;


--
-- TOC entry 2914 (class 2606 OID 32843)
-- Name: books fk_categories_books; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT fk_categories_books FOREIGN KEY (category_id) REFERENCES public.categories(category_id);


--
-- TOC entry 2917 (class 2606 OID 32880)
-- Name: orders fk_customers_orders; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk_customers_orders FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id) NOT VALID;


--
-- TOC entry 2919 (class 2606 OID 32925)
-- Name: order_details orderDetails_bookId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT "orderDetails_bookId_fkey" FOREIGN KEY (book_id) REFERENCES public.books(book_id);


--
-- TOC entry 2918 (class 2606 OID 32920)
-- Name: order_details orderDetails_orderId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT "orderDetails_orderId_fkey" FOREIGN KEY (order_id) REFERENCES public.orders(order_id);


--
-- TOC entry 2921 (class 2606 OID 32940)
-- Name: ratings ratings_bookId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT "ratings_bookId_fkey" FOREIGN KEY (book_id) REFERENCES public.books(book_id);


--
-- TOC entry 2920 (class 2606 OID 32935)
-- Name: ratings ratings_customerId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT "ratings_customerId_fkey" FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id);


-- Completed on 2021-06-30 21:08:05

--
-- PostgreSQL database dump complete
--

