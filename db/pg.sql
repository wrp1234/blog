-- 创建文件表
CREATE TABLE IF NOT EXISTS public.file
(
    id bigserial,
    create_time timestamp without time zone NOT NULL DEFAULT now(),
    update_time timestamp without time zone NOT NULL DEFAULT now(),
    deleted smallint NOT NULL DEFAULT 0,
    user_id bigint NOT NULL,
    url character varying  NOT NULL,
    file_name character varying  NOT NULL,
    size bigint NOT NULL,
    type smallint NOT NULL,
    CONSTRAINT file_pkey PRIMARY KEY (id)
);

-- 创建用户表
CREATE TABLE IF NOT EXISTS public.b_user
(
    id bigserial,
    create_time timestamp without time zone NOT NULL DEFAULT now(),
    update_time timestamp without time zone NOT NULL DEFAULT now(),
    deleted smallint NOT NULL DEFAULT 0,
    username character varying  NOT NULL,
    password character varying  NOT NULL,
    phone character varying(11)  NOT NULL,
    introduction character varying ,
    birthday date,
    photo character varying ,
    CONSTRAINT user_pkey PRIMARY KEY (id)
);
-- 用户表电话设置唯一索引
CREATE UNIQUE INDEX IF NOT EXISTS b_user_phone_idx
    ON public.b_user USING btree
    (phone COLLATE pg_catalog."default" ASC NULLS LAST)
    WITH (deduplicate_items=True)
    TABLESPACE pg_default;
-- 用户表用户名设置唯一索引
CREATE UNIQUE INDEX IF NOT EXISTS b_user_username_idx
    ON public.b_user USING btree
    (username COLLATE pg_catalog."default" ASC NULLS LAST)
    WITH (deduplicate_items=True)
    TABLESPACE pg_default;

-- 创建博客
CREATE TABLE IF NOT EXISTS public.article
(
    id bigserial,
    create_time timestamp without time zone NOT NULL DEFAULT now(),
    update_time timestamp without time zone NOT NULL DEFAULT now(),
    deleted smallint NOT NULL DEFAULT 0,
    user_id bigint NOT NULL,
    catalog_id bigint NOT NULL,
    photo_id bigint NOT NULL,
    title character varying  NOT NULL,
    abstract_content character varying  NOT NULL,
    content text NOT NULL,
    CONSTRAINT article_pkey PRIMARY KEY (id)
);

-- 创建目录表
CREATE TABLE IF NOT EXISTS public.catalog
(
    id bigserial,
    create_time timestamp without time zone NOT NULL DEFAULT now(),
    update_time timestamp without time zone NOT NULL DEFAULT now(),
    deleted smallint NOT NULL DEFAULT 0,
    user_id bigint NOT NULL,
    name character varying  NOT NULL,
    description character varying  NOT NULL,
    article_count int NOT NULL,
    CONSTRAINT catalog_pkey PRIMARY KEY (id)
);