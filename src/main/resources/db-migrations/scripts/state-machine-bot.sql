create table states
(
    user_id bigint primary key, --chat_id
    state_id          bigint  not null,
    state_data        jsonb not null
)