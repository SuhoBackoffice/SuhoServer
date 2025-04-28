ALTER TABLE project_branch
    ADD CONSTRAINT uq_project_branch_project_id_branch_code UNIQUE (project_id, branch_code);
