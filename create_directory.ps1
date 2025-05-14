# 1. Ask user for directory name
$dir_name = Read-Host "Enter the name of the directory you want to create"

# 2. Try to create the directory
try {
    New-Item -Path $dir_name -ItemType Directory -ErrorAction Stop
    Write-Host "Directory created successfully"
    
    # 3. Try to create txt.log file inside the directory
    try {
        New-Item -Path "$dir_name\txt.log" -ItemType File -ErrorAction Stop
        Write-Host "File created successfully"
    }
    catch {
        Write-Host "Error: Could not create txt.log file"
        exit 1
    }
}
catch {
    Write-Host "Error: Could not create directory $dir_name"
    exit 1
} 