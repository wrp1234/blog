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