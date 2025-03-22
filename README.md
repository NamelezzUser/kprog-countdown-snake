# 🐍 Snake – Original Project Notes

## Getting Started

```
# Suggested Git setup:
cd existing_repo
git remote add origin https://git.it.hs-heilbronn.de/it/courses/seb/kprog/kprog-2023-ws.git
git branch -M main
git push -uf origin main
```

## Essential Git Commands

```
git status                  # Show status of current branch
git pull                    # Sync local branch with remote
git add ./foldername
git commit -m "Descriptive commit message"
git push                    # Always push to your own branch first
git merge branchname        # Merge the selected branch into the current one
```

## Useful Terminal Commands

```
cd foldername         # Navigate into a folder
ls                    # List folder contents
mkdir newFolder       # Create a new folder
rm filename           # Delete a file
```
---
## Project Introduction

Welcome to our Snake game project developed for the Komplexe Programme module.
We chose a *single-screen* design to keep the implementation focused and efficient. Below are our reasons:

## Design Choices

**Functionality First**: A minimal UI allowed us to concentrate on implementing core gameplay features.

**Time & Resource Efficiency**: Focused development within the project timeframe.

**User-Friendly**: One screen simplifies player interaction and enhances usability.

**Risk Minimization**: A simpler structure reduces potential bugs and improves maintainability.

## Mockup Implementation

While the original plan included multiple screens, we decided a single, state-driven interface was more effective.
UI elements are enabled or disabled depending on the game state, providing clear configuration and gameplay within one unified interface.

## Conclusion

This version of Snake lays a solid foundation for future expansion – including visual improvements, added features, and enhanced gameplay.

---