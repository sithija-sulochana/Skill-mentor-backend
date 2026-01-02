#!/bin/bash
# Helper script to complete the branch merge fix
# This script shows the exact steps needed to update the remote branches

echo "=========================================="
echo "Branch Merge Fix - Completion Script"
echo "=========================================="
echo ""
echo "This script will update master and feature-adding_entities branches"
echo "to use the rewritten history with a common ancestor."
echo ""
echo "⚠️  WARNING: This will rewrite history on remote branches!"
echo "Press Ctrl+C to cancel, or Enter to continue..."
read

# Configuration
REPO_DIR="/home/runner/work/Skill-mentor-backend/Skill-mentor-backend"
TARGET_COMMIT="912af1c322165243e07e4196c6de9c254b3a11d6"

cd "$REPO_DIR" || exit 1

echo ""
echo "Step 1: Verifying the target commit exists..."
if git cat-file -e "$TARGET_COMMIT" 2>/dev/null; then
    echo "✓ Target commit $TARGET_COMMIT found"
    git --no-pager show --oneline --stat "$TARGET_COMMIT" | head -5
else
    echo "✗ Target commit not found!"
    exit 1
fi

echo ""
echo "Step 2: Checking current branch status..."
git --no-pager branch -a | grep -E "(master|feature-adding_entities)"

echo ""
echo "Step 3: Updating master branch..."
git checkout master
git reset --hard "$TARGET_COMMIT"
echo "✓ Master branch updated to $TARGET_COMMIT"

echo ""
echo "Step 4: Updating feature-adding_entities branch..."
git checkout feature-adding_entities
git reset --hard "$TARGET_COMMIT"
echo "✓ feature-adding_entities branch updated to $TARGET_COMMIT"

echo ""
echo "Step 5: Displaying the unified history..."
git --no-pager log --oneline --graph --decorate -10

echo ""
echo "=========================================="
echo "Local branches have been updated!"
echo "=========================================="
echo ""
echo "To push these changes to GitHub, you would need to run:"
echo ""
echo "  git push --force origin master"
echo "  git push --force origin feature-adding_entities"
echo ""
echo "⚠️  Note: This script cannot push due to permission restrictions."
echo "Please run the push commands manually if you have push access."
echo ""
