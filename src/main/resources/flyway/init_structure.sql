create table employee
(
	id bigserial not null
		constraint employee_pkey
			primary key,
	firstname varchar(255),
	lastname varchar(255)
)
;

create table match
(
	id bigserial not null
		constraint match_pkey
			primary key,
	goals1 integer,
	goals2 integer,
	team1_id bigint
		constraint fk_match_team1_id
			references team,
	team2_id bigint
		constraint fk_match_team2_id
			references team
)
;

create table team
(
	id bigserial not null
		constraint team_pkey
			primary key,
	p1_id bigint
		constraint fk_team_p1_id
			references employee,
	p2_id bigint
		constraint fk_team_p2_id
			references employee
)
;


CREATE SEQUENCE public.employee_id_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.employee ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq');
ALTER SEQUENCE public.employee_id_seq OWNED BY public.employee.id;

CREATE SEQUENCE public.match_id_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.match ALTER COLUMN id SET DEFAULT nextval('public.match_id_seq');
ALTER SEQUENCE public.match_id_seq OWNED BY public.match.id;

CREATE SEQUENCE public.team_id_seq NO MINVALUE NO MAXVALUE NO CYCLE;
ALTER TABLE public.team ALTER COLUMN id SET DEFAULT nextval('public.team_id_seq');
ALTER SEQUENCE public.team_id_seq OWNED BY public.team.id;