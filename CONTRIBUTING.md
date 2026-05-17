Contributing
============

Regras e convenções rápidas para contribuir com este repositório.

1. Branching
   - Nome das branches:
     - feat/<descrição-cura>
     - fix/<descrição-curta>
     - chore/<descrição>
   - Faça rebase contra `main` antes de abrir PR (ou squashed merge se for política do time).

2. Commits
   - Use mensagens claras: `<type>(scope): descrição curta`
   - type: feat, fix, chore, docs, test, refactor

3. Pull Request
   - Preencha o template em `.github/PULL_REQUEST_TEMPLATE.md`.
   - Adicione reviewers e labels apropriados (ex: area/catalog, tipo/feature, prioridade/high).
   - Relacione a issue usando `#<número>`.
   - Obrigatório: build passando e testes locais executados.

4. Revisão de Código
   - Revisores devem checar:
     - Legibilidade e design
     - Cobertura de testes para mudanças críticas
     - Não expor segredos (variáveis de ambiente em `application.yaml` para dev somente)

5. CI e Quality Gates
   - Este repositório exige que o CI execute: build, testes e (se configurado) lint.
   - PRs com falhas no CI devem ser corrigidos antes do merge.

6. Deploy / Migrations
   - Documente migrações necessárias e plano de rollback na seção "Observações de deploy" do PR.

7. Código e Estilo
   - Siga as convenções do projeto (Java 11+, Gradle). Execute `./gradlew build` antes de enviar.

Se tiver dúvidas, abra uma issue ou fale com o time de maintainers.

