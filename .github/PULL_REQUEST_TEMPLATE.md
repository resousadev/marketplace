<!--
  Pull Request Template - empresa-style
  Segue um template em português com seções práticas para revisão rápida e testes.
  Preencha os campos abaixo antes de solicitar revisão.
-->

# Título

<!-- Use um título curto e descritivo: <tipo>(escopo): descrição curta -->
Ex: feat(catalog): adicionar endpoint de busca por cidade

---

## Descrição
- O que foi feito? (Breve resumo)
- Por que foi necessário? (Motivação / contexto)
- Pontos importantes de implementação

## Tipo de mudança
- [ ] Bugfix
- [ ] Nova feature
- [ ] Refactor
- [ ] Testes
- [ ] Docs
- [ ] CI/CD
- [ ] Outros (especifique): ______

## Issue relacionada
- Resolve/Refere: #<número-da-issue>

## Checklist de revisão (preencher antes do review)
- [ ] Código compilou localmente: `./gradlew build` ✅
- [ ] Todos os testes unitários passam: `./gradlew test` ✅
- [ ] Testes manuais executados (se aplicável) — passos abaixo
- [ ] Alterações documentadas (README / docs / comentários)
- [ ] Não há segredos/credenciais no PR
- [ ] Labels aplicados (type/area/priority)
- [ ] Revisores atribuídos
- [ ] PR squashed/rebase pronto para merge (conforme política de merge)

## Como testar (passos para QA / reviewer)
1. Checkout da branch: `git fetch origin && git checkout <branch>`
2. Rodar build: `./gradlew clean build`
3. Executar testes: `./gradlew test` ou executar testes específicos
4. Passos manuais (se aplicáveis):
   - Endpoint: GET /api/showcase
   - Dados de exemplo: ...

## Observações de deploy / breaking changes
- Há mudanças que exigem migração de dados? (sim/não) — se sim, descreva
- Variáveis de ambiente novas/alteradas:
  - NOME_VAR: descrição

## Screenshots / GIFs (se alterou UI)
- (anexe imagens ou links)

## Impacto esperado
- Estimar risco e rollback plan

---

Obrigado por seguir o template — isso ajuda a manter revisão rápida e consistente.

